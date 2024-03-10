package org.example.infrastructure.restaurant.commands.admin

import org.example.infrastructure.restaurant.commands.Command
import org.example.infrastructure.restaurant.system.entities.System
import org.example.infrastructure.restaurant.system.entities.Workplace
import org.example.infrastructure.restaurant.system.results.WorkplaceResult

class CreateDishCommand(private val workplace: Workplace):Command {
    override var system: System = workplace
    override suspend fun execute() {
        val dish = workplace.workplaceInterface.getNewDish()
        workplace.workplaceInterface.displayResult(
            if (workplace.kitchen.menuService.createDish(dish))
                WorkplaceResult.OK
            else
                WorkplaceResult.DISH_ALREADY_EXISTS
        )
    }

    companion object {
        override fun toString(): String = "create"
    }
}