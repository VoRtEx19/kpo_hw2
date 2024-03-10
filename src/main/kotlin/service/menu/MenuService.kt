package org.example.service.menu

import org.example.database.repository.menu.DishRepository
import org.example.models.dish.Dish

interface MenuService {
    var dishRepository: DishRepository

    // These are dangerous, don't use them. Instead, add or remove
    fun createDish(dish: Dish): Boolean
    fun deleteDish(dish: Dish)


    fun getMenu(): List<Dish>
    fun getAllAssortment(): List<Dish>
    fun exists(dish: Dish): Boolean
    fun getPortionsLeft(dish: Dish): UInt?

    fun addDish(dish: Dish): Boolean
    fun removeDish(dish: Dish): Boolean
    fun editPortionCount(dish: Dish, newPortionCount: UInt): Dish?
    fun editPrice(dish: Dish, newPrice: Double): Dish?
    fun editCookingTime(dish: Dish, newCookingTime: UInt): Dish?
}