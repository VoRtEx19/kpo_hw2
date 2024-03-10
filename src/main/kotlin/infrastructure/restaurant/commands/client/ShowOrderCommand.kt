package org.example.infrastructure.restaurant.commands.client

import org.example.infrastructure.restaurant.commands.Command
import org.example.infrastructure.restaurant.system.entities.System
import org.example.infrastructure.restaurant.system.entities.Waiter

class ShowOrderCommand(private val waiter: Waiter): Command {
    override var system: System = waiter

    override suspend fun execute() {
        waiter.waiterInterface.displayOrder(waiter.client.order)
    }

    companion object {
        override fun toString(): String = "show"
    }
}