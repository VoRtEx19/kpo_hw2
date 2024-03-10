package org.example.infrastructure.restaurant.system.entities

import org.example.infrastructure.restaurant.commands.Command
import org.example.infrastructure.restaurant.commands.HelpCommand
import org.example.infrastructure.restaurant.kitchen.Kitchen
import org.example.infrastructure.restaurant.system.interfaces.SystemInterface
import org.example.models.user.User
import org.example.service.menu.MenuService

abstract class System(val user: User, val kitchen: Kitchen, val systemInterface: SystemInterface) {
    protected abstract val commands: Map<String, Command>

    suspend fun launch(command: String) {
        commands[command]?.execute() ?: throw IllegalArgumentException("Unknown command")
    }

    abstract suspend fun serveUser()
}