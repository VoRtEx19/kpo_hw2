package org.example.database.repository.menu

data class DishDTO(
    val name: String,
    val ingredients: List<String>,
    var portionCount: UInt,
    var price: Double,
    var cookingTime: UInt,
    var isOnMenu: Boolean
)
