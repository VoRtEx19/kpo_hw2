package org.example.infrastructure.restaurant.commands.client

import org.example.infrastructure.restaurant.commands.Command
import org.example.infrastructure.restaurant.system.entities.System
import org.example.infrastructure.restaurant.system.entities.Waiter
import org.example.infrastructure.restaurant.system.results.WaiterResult
import org.example.models.order.OrderStatus

class CancelOrderCommand(private val waiter: Waiter) : Command {
    override var system: System = waiter

    override suspend fun execute() {
        waiter.waiterInterface.displayResult(
            when (waiter.client.order?.status) {
                null -> WaiterResult.ORDER_NOT_EXISTS
                OrderStatus.CANCELLED -> WaiterResult.ORDER_CANCELLED
                OrderStatus.READY -> WaiterResult.ORDER_ALREADY_READY
                else -> {
                    waiter.kitchen.cancelOrder(waiter.client.order!!)
                    WaiterResult.OK
                }
            }
        )
    }

    companion object {
        override fun toString(): String = "cancel"
    }
}