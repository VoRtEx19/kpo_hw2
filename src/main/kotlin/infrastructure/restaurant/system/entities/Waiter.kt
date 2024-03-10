package org.example.infrastructure.restaurant.system.entities

import org.example.infrastructure.restaurant.commands.Command
import org.example.infrastructure.restaurant.commands.DishInfoCommand
import org.example.infrastructure.restaurant.commands.HelpCommand
import org.example.infrastructure.restaurant.commands.client.ShowMenuCommand
import org.example.infrastructure.restaurant.commands.client.*
import org.example.infrastructure.restaurant.kitchen.Kitchen
import org.example.infrastructure.restaurant.system.interfaces.WaiterInterface
import org.example.models.user.Client

class Waiter(val client: Client, kitchen: Kitchen, val waiterInterface: WaiterInterface) : System(client, kitchen, waiterInterface) {
    override val commands: Map<String, Command> = mapOf(
        HelpCommand.toString() to HelpCommand(this),
        DishInfoCommand.toString() to DishInfoCommand(this),
        ShowMenuCommand.toString() to ShowMenuCommand(this),
        AddDishCommand.toString() to AddDishCommand(this),
        RemoveDishCommand.toString() to RemoveDishCommand(this),
        CancelOrderCommand.toString() to CancelOrderCommand(this),
        OrderStatusCommand.toString() to OrderStatusCommand(this),
        ShowOrderCommand.toString() to ShowOrderCommand(this),
        PlaceOrderCommand.toString() to PlaceOrderCommand(this),
        SubmitOrderCommand.toString() to SubmitOrderCommand(this)
    )

    override suspend fun serveUser() {
        waiterInterface.intro()
        while (true) {
            val input = waiterInterface.getCommand(commands.keys.toList().plus("exit"))
            if (input == "exit")
                break
            launch(input)
        }
        waiterInterface.outro()
    }
}