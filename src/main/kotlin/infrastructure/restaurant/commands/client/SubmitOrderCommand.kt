package org.example.infrastructure.restaurant.commands.client

import org.example.infrastructure.restaurant.commands.Command
import org.example.infrastructure.restaurant.system.entities.System
import org.example.infrastructure.restaurant.system.entities.Waiter
import org.example.infrastructure.restaurant.system.results.WaiterResult
import org.example.models.order.OrderStatus

class SubmitOrderCommand(private val waiter: Waiter) : Command {
    override var system: System = waiter

    override suspend fun execute() {
        waiter.waiterInterface.displayResult(
            when (waiter.client.order?.status) {
                null -> WaiterResult.ORDER_NOT_EXISTS
                OrderStatus.CANCELLED -> WaiterResult.ORDER_CANCELLED
                OrderStatus.READY -> WaiterResult.ORDER_ALREADY_READY
                OrderStatus.RECEIVED -> WaiterResult.ORDER_RECEIVED
                OrderStatus.PAID -> WaiterResult.ORDER_PAID
                OrderStatus.COOKING -> WaiterResult.ORDER_COOKING
                OrderStatus.CREATING -> {
                    val dishes = waiter.kitchen.addOrder(waiter.client.order!!, waiter)
                    if (dishes.isEmpty()) {
                        waiter.client.order!!.status = OrderStatus.RECEIVED
                        WaiterResult.OK
                    } else {
                        waiter.waiterInterface.displayDishesThatFinished(dishes)
                        dishes.forEach { waiter.client.order!!.removeDish(it) }
                        WaiterResult.INSUFFICIENT_AMOUNT_DISHES
                    }
                }
            }
        )
    }

    companion object {
        override fun toString(): String = "submit"
    }
}