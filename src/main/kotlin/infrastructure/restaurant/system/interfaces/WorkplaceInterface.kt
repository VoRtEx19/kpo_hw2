package org.example.infrastructure.restaurant.system.interfaces

import org.example.infrastructure.restaurant.commands.admin.EditDishCommand
import org.example.infrastructure.restaurant.system.results.WorkplaceResult
import org.example.models.dish.Dish

interface WorkplaceInterface : SystemInterface {
    fun getNewDish(): Dish
    override fun getCommand(commandList: List<String>): String
    fun getEditOption(): EditDishCommand.EditOption
    fun getNewUIntValue(editOption: EditDishCommand.EditOption): UInt?
    fun getNewDoubleValue(): Double?
    fun displayResult(workplaceResult: WorkplaceResult)
    fun displayRevenue(revenue: UInt)
}