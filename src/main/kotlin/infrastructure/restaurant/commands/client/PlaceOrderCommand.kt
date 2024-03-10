package org.example.infrastructure.restaurant.commands.client

import org.example.infrastructure.restaurant.commands.Command
import org.example.infrastructure.restaurant.system.entities.System
import org.example.infrastructure.restaurant.system.entities.Waiter
import org.example.infrastructure.restaurant.system.results.WaiterResult
import org.example.models.order.Order
import org.example.models.order.OrderStatus


class PlaceOrderCommand(private val waiter: Waiter) : Command {
    override var system: System = waiter

    override suspend fun execute() {
        waiter.waiterInterface.displayResult(
            when(waiter.client.order?.status) {
                OrderStatus.CREATING -> WaiterResult.ORDER_CREATING
                OrderStatus.RECEIVED -> WaiterResult.ORDER_RECEIVED
                OrderStatus.COOKING -> WaiterResult.ORDER_COOKING
                OrderStatus.READY -> WaiterResult.ORDER_ALREADY_READY
                else -> {
                    waiter.client.order = Order()
                    WaiterResult.OK
                }
            }
        )
    }

    companion object {
        override fun toString(): String = "order"
    }
}