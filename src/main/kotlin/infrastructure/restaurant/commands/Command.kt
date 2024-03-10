package org.example.infrastructure.restaurant.commands

import org.example.infrastructure.restaurant.system.entities.System

interface Command {
    var system: System

    suspend fun execute()
}