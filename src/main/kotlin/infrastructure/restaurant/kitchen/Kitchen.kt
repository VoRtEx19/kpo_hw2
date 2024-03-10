package org.example.infrastructure.restaurant.kitchen

import org.example.service.menu.MenuService
import kotlinx.coroutines.*
import org.example.infrastructure.restaurant.system.entities.Waiter
import org.example.models.dish.Dish
import org.example.models.order.Order
import org.example.models.order.OrderStatus
import org.example.service.revenue.RevenueService
import java.util.LinkedList
import java.util.Queue

class Kitchen(val menuService: MenuService, val revenueService: RevenueService) {
    private val orders: Queue<Pair<Order, Waiter>> = LinkedList()
    private var cook: Job? = null

    suspend fun addOrder(order: Order, waiter: Waiter): List<Dish> {
        val result = order.dishes.filter { menuService.getPortionsLeft(it) == 0U }
        if (result.isNotEmpty()) {
            result.forEach { menuService.removeDish(it) }
        } else {
            order.dishes.forEach { menuService.editPortionCount(it, --it.portionsLeft) }
            orders.add(order to waiter)
            if (orders.size == 1) cook = GlobalScope.launch {
                startWorking()
            }
        }
        return result
    }

    fun cancelOrder(order: Order) = runBlocking{
        if (orders.peek().first == order) cook?.cancelAndJoin()
        orders.removeIf { order == it.first }
        order.status = OrderStatus.CANCELLED
        order.dishes.forEach { menuService.editPortionCount(it, ++it.portionsLeft) }
        if (orders.isNotEmpty())
            cook = GlobalScope.launch {
                startWorking()
            }
    }

    private suspend fun startWorking() {
        while (orders.isNotEmpty()) {
            val (order, waiter) = orders.peek()
            order.status = OrderStatus.COOKING
            delay(order.getTotalCookingTime().toLong() * 1000L)
            if (order.status == OrderStatus.COOKING) {
                val res = waiter.waiterInterface.orderReady()
                order.status = OrderStatus.PAID
                revenueService.addRevenue(order.getTotalPrice().toUInt())
            }
            orders.poll()
        }
    }
}