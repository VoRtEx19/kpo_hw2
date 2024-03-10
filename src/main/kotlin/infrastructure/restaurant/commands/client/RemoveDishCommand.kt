package org.example.infrastructure.restaurant.commands.client

import org.example.infrastructure.restaurant.commands.Command
import org.example.infrastructure.restaurant.system.entities.System
import org.example.infrastructure.restaurant.system.entities.Waiter
import org.example.infrastructure.restaurant.system.results.WaiterResult
import org.example.models.order.OrderStatus

class RemoveDishCommand(private val waiter: Waiter) : Command {
    override var system: System = waiter
    override suspend fun execute() {
        waiter.waiterInterface.displayResult(
            when (waiter.client.order?.status) {
                null -> WaiterResult.ORDER_NOT_EXISTS
                OrderStatus.READY -> WaiterResult.ORDER_ALREADY_READY
                OrderStatus.PAID -> WaiterResult.ORDER_PAID
                OrderStatus.CANCELLED -> WaiterResult.ORDER_CANCELLED
                else -> {
                    val dish = waiter.waiterInterface.getDish(waiter.kitchen.menuService.getMenu())
                    if (dish == null) WaiterResult.DISH_CHOICE_CANCELLED
                    if (waiter.client.order!!.removeDish(dish!!)) WaiterResult.OK
                    else WaiterResult.NO_SUCH_DISH_IN_ORDER
                }
            }
        )
    }

    companion object {
        override fun toString(): String = "remove"
    }
}