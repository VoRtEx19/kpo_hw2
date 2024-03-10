package org.example.infrastructure.restaurant.commands.admin

import org.example.infrastructure.restaurant.commands.Command
import org.example.infrastructure.restaurant.system.entities.System
import org.example.infrastructure.restaurant.system.entities.Workplace

class ShowAssortmentCommand(private val workplace: Workplace) : Command {
    override var system: System = workplace
    override suspend fun execute() {
        workplace.workplaceInterface.displayMenu(system.kitchen.menuService.getAllAssortment())
    }

    companion object {
        override fun toString(): String = "menu"
    }
}