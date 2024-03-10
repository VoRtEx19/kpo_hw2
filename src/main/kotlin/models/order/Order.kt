package org.example.models.order

import org.example.models.dish.Dish

class Order {
    var id = -1
    var status: OrderStatus = OrderStatus.CREATING
    val dishes = mutableListOf<Dish>()

    fun addDish(dish: Dish): Boolean {
        if (status != OrderStatus.READY && status != OrderStatus.CANCELLED) {
            dishes.add(dish)
            return true
        }
        return false
    }

    fun removeDish(dish: Dish): Boolean = dishes.remove(dish)

    fun getTotalPrice(): Double = dishes.sumOf { it.price }

    fun getTotalCookingTime(): UInt = dishes.sumOf { it.cookingTime }

    fun cancel() {
        status = OrderStatus.CANCELLED
    }
}