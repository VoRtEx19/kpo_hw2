package org.example.service.menu

import org.example.database.repository.menu.DishDTO
import org.example.models.dish.Dish

fun Dish.toDishDTO() = DishDTO(name, ingredients, portionsLeft, price, cookingTime, isOnMenu)
fun DishDTO.toDish() = Dish(name, ingredients, portionCount, price, cookingTime, isOnMenu)