package org.example.infrastructure.restaurant.commands.admin

import org.example.infrastructure.restaurant.commands.Command
import org.example.infrastructure.restaurant.system.entities.System
import org.example.infrastructure.restaurant.system.entities.Workplace

class GetRevenueCommand(private val workplace: Workplace): Command {
    override var system: System= workplace

    override suspend fun execute() {
        workplace.workplaceInterface.displayRevenue(workplace.kitchen.revenueService.getTotalRevenue())
    }

    companion object{
        override fun toString(): String = "revenue"
    }
}