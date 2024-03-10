package org.example.infrastructure.restaurant.commands.client

import org.example.infrastructure.restaurant.commands.Command
import org.example.infrastructure.restaurant.system.entities.System
import org.example.infrastructure.restaurant.system.entities.Waiter

class OrderStatusCommand(private val waiter: Waiter) : Command {
    override var system: System = waiter

    override suspend fun execute() {
        waiter.waiterInterface.displayOrderStatus(waiter.client.order?.status)
    }

    companion object {
        override fun toString(): String = "status"
    }
}