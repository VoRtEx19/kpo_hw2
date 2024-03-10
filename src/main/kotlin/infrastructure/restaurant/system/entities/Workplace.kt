package org.example.infrastructure.restaurant.system.entities

import org.example.infrastructure.restaurant.commands.Command
import org.example.infrastructure.restaurant.commands.DishInfoCommand
import org.example.infrastructure.restaurant.commands.HelpCommand
import org.example.infrastructure.restaurant.commands.admin.*
import org.example.infrastructure.restaurant.kitchen.Kitchen
import org.example.infrastructure.restaurant.system.interfaces.WorkplaceInterface
import org.example.models.user.Admin

class Workplace(admin: Admin, kitchen: Kitchen, val workplaceInterface: WorkplaceInterface): System(admin, kitchen, workplaceInterface) {
    override val commands: Map<String, Command> = mapOf(
        HelpCommand.toString() to HelpCommand(this),
        DishInfoCommand.toString() to DishInfoCommand(this),
        ShowAssortmentCommand.toString() to ShowAssortmentCommand(this),
        GetRevenueCommand.toString() to GetRevenueCommand(this),
        CreateDishCommand.toString() to CreateDishCommand(this),
        AddDishToMenuCommand.toString() to AddDishToMenuCommand(this),
        EditDishCommand.toString() to EditDishCommand(this),
        RemoveDishFromMenuCommand.toString() to RemoveDishFromMenuCommand(this)
    )

    override suspend fun serveUser() {
        workplaceInterface.intro()
        while (true) {
            val input = workplaceInterface.getCommand(commands.keys.toList().plus("exit"))
            if (input == "exit")
                break
            launch(input)
        }
        workplaceInterface.outro()
    }
}