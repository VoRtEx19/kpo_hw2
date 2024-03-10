package org.example.infrastructure.restaurant.commands.client

import org.example.infrastructure.restaurant.commands.Command
import org.example.infrastructure.restaurant.system.entities.*

class ShowMenuCommand(private val waiter: Waiter) : Command {
    override var system: System = waiter
    override suspend fun execute() {
        waiter.waiterInterface.displayMenu(system.kitchen.menuService.getMenu())
    }

    companion object {
        override fun toString(): String = "menu"
    }
}