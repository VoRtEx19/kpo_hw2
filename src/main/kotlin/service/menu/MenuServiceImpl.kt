package org.example.service.menu

import org.example.database.repository.menu.DishRepository
import org.example.models.dish.Dish

class MenuServiceImpl(override var dishRepository: DishRepository) : MenuService {
    override fun createDish(dish: Dish): Boolean {
        if (exists(dish))
            return false
        return dishRepository.create(dish.toDishDTO())
    }

    override fun deleteDish(dish: Dish) {
        dishRepository.delete(dish.name)
    }

    override fun getAllAssortment(): List<Dish> = dishRepository.getAll().map { it.toDish() }

    override fun exists(dish: Dish): Boolean = dishRepository.getById(dish.name) != null

    override fun getPortionsLeft(dish: Dish): UInt? = dishRepository.getById(dish.name)?.portionCount

    override fun addDish(dish: Dish): Boolean {
        if (dishRepository.getById(dish.name)?.isOnMenu != false)
            return false
        val dishDTO = dish.toDishDTO()
        dishDTO.isOnMenu = true
        return dishRepository.update(dishDTO)
    }

    override fun removeDish(dish: Dish): Boolean {
        if (dishRepository.getById(dish.name)?.isOnMenu != true)
            return false
        val dishDTO = dish.toDishDTO()
        dishDTO.isOnMenu = false
        return dishRepository.update(dishDTO)
    }

    override fun editPortionCount(dish: Dish, newPortionCount: UInt): Dish? {
        if (dishRepository.getById(dish.name) == null)
            return null
        dish.portionsLeft = newPortionCount
        dishRepository.update(dish.toDishDTO())
        return dish
    }

    override fun editPrice(dish: Dish, newPrice: Double): Dish? {
        if (dishRepository.getById(dish.name) == null)
            return null
        dish.price = newPrice
        dishRepository.update(dish.toDishDTO())
        return dish
    }

    override fun editCookingTime(dish: Dish, newCookingTime: UInt): Dish? {
        if (dishRepository.getById(dish.name) == null)
            return null
        dish.cookingTime = newCookingTime
        dishRepository.update(dish.toDishDTO())
        return dish
    }

    override fun getMenu(): List<Dish> = dishRepository.getAll().filter { it.isOnMenu }.map { it.toDish() }
}