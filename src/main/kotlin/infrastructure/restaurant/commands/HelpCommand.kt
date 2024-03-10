package org.example.infrastructure.restaurant.commands

import org.example.infrastructure.restaurant.system.entities.System

class HelpCommand(override var system: System) : Command {
    override suspend fun execute() {
        system.systemInterface.displayHelp()
    }

    companion object {
        override fun toString(): String = "help"
    }
}