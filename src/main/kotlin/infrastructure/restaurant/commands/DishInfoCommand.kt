package org.example.infrastructure.restaurant.commands

import org.example.infrastructure.restaurant.system.entities.System
import org.example.infrastructure.restaurant.system.entities.Waiter
import org.example.infrastructure.restaurant.system.entities.Workplace

class DishInfoCommand(override var system: System) : Command {
    override suspend fun execute() {
        system.systemInterface.displayDishInfo(
            system.systemInterface.getDish(
                when (system) {
                    is Waiter -> system.kitchen.menuService.getMenu()
                    is Workplace -> system.kitchen.menuService.getAllAssortment()
                    else -> throw RuntimeException("Unknown system type: ${system.javaClass.name}")
                }
            )
        )
    }

    companion object {
        override fun toString(): String = "info"
    }
}