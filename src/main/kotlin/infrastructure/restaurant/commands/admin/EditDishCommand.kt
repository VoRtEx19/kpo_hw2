package org.example.infrastructure.restaurant.commands.admin

import org.example.infrastructure.restaurant.commands.Command
import org.example.infrastructure.restaurant.system.entities.System
import org.example.infrastructure.restaurant.system.entities.Workplace
import org.example.infrastructure.restaurant.system.results.WorkplaceResult

class EditDishCommand(private val workplace: Workplace) : Command {
    override var system: System = workplace

    override suspend fun execute() {
        var dish = workplace.workplaceInterface.getDish(workplace.kitchen.menuService.getAllAssortment())
        if (dish == null) {
            workplace.workplaceInterface.displayResult(WorkplaceResult.CANCELLED)
            return
        }

        val editOption = workplace.workplaceInterface.getEditOption()
        if (editOption != EditOption.PRICE) {
            val newValue = workplace.workplaceInterface.getNewUIntValue(editOption)
            if (newValue == null) {
                workplace.workplaceInterface.displayResult(WorkplaceResult.CANCELLED)
                return
            }
            dish = if (editOption == EditOption.PORTION_COUNT)
                workplace.kitchen.menuService.editPortionCount(dish, newValue)
            else
                workplace.kitchen.menuService.editCookingTime(dish, newValue)
        } else {
            val newValue = workplace.workplaceInterface.getNewDoubleValue()
            if (newValue == null) {
                workplace.workplaceInterface.displayResult(WorkplaceResult.CANCELLED)
                return
            }
            dish = workplace.kitchen.menuService.editPrice(dish, newValue)
        }
        workplace.workplaceInterface.displayResult(
            if (dish != null)
                WorkplaceResult.OK
            else
                WorkplaceResult.NON_EXISTING_DISH
        )
    }

    companion object {
        override fun toString(): String = "edit"
    }

    enum class EditOption {
        PRICE,
        PORTION_COUNT,
        COOKING_TIME
    }
}