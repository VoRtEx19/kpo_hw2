package org.example.infrastructure.restaurant.commands.client

import org.example.infrastructure.restaurant.commands.Command
import org.example.infrastructure.restaurant.system.entities.System
import org.example.infrastructure.restaurant.system.entities.Waiter
import org.example.infrastructure.restaurant.system.results.WaiterResult
import org.example.models.order.Order
import org.example.models.order.OrderStatus

class AddDishCommand(private val waiter: Waiter) : Command {
    override var system: System = waiter

    override suspend fun execute() {
        val wasNull = waiter.client.order == null
        if (wasNull) waiter.client.order = Order()
        val dish = waiter.waiterInterface.getDish(waiter.kitchen.menuService.getMenu())
        waiter.waiterInterface.displayResult(
            if (dish == null) WaiterResult.DISH_CHOICE_CANCELLED
            else {
                if (waiter.client.order!!.addDish(dish)) WaiterResult.OK
                else when (waiter.client.order!!.status) {
                    OrderStatus.READY -> WaiterResult.ORDER_ALREADY_READY
                    OrderStatus.CANCELLED -> WaiterResult.ORDER_CANCELLED
                    else -> throw RuntimeException("Unknown order status ${waiter.client.order!!.status} in addDishCommand")
                }
            }
        )
        if (wasNull) PlaceOrderCommand(waiter).execute()
    }

    companion object {
        override fun toString(): String = "add"
    }
}