package org.example.infrastructure.restaurant.commands.admin

import org.example.infrastructure.restaurant.commands.Command
import org.example.infrastructure.restaurant.system.entities.System
import org.example.infrastructure.restaurant.system.entities.Workplace
import org.example.infrastructure.restaurant.system.results.WorkplaceResult

class RemoveDishFromMenuCommand(private val workplace: Workplace) : Command {
    override var system: System = workplace

    override suspend fun execute() {
        val dish = workplace.workplaceInterface.getDish(workplace.kitchen.menuService.getMenu())
        workplace.workplaceInterface.displayResult(
            if (dish == null)
                WorkplaceResult.CANCELLED
            else if (!workplace.kitchen.menuService.exists(dish))
                WorkplaceResult.NON_EXISTING_DISH
            else if (!workplace.kitchen.menuService.removeDish(dish)) {
                WorkplaceResult.DISH_ALREADY_OFF_MENU
            } else
                WorkplaceResult.OK
        )
    }

    companion object {
        override fun toString(): String = "remove"
    }
}