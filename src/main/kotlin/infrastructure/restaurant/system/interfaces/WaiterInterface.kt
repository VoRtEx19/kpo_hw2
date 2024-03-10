package org.example.infrastructure.restaurant.system.interfaces

import org.example.infrastructure.restaurant.system.results.WaiterResult
import org.example.models.dish.Dish
import org.example.models.order.Order
import org.example.models.order.OrderStatus

interface WaiterInterface: SystemInterface {
    fun displayResult(waiterResult: WaiterResult)
    fun displayOrder(order: Order?)
    fun displayOrderStatus(status: OrderStatus?)
    fun orderReady()
    fun displayDishesThatFinished(dishes: List<Dish>)
}