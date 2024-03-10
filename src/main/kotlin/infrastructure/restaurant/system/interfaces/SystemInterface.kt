package org.example.infrastructure.restaurant.system.interfaces

import org.example.models.dish.Dish

interface SystemInterface {
    fun intro()
    fun outro()
    fun displayHelp()
    fun displayMenu(menu: List<Dish>)
    fun displayDishInfo(dish: Dish?)
    fun getCommand(commandList: List<String>): String
    fun getDish(menu: List<Dish>): Dish?
}