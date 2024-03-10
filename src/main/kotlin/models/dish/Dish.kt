package org.example.models.dish

import org.example.application.console.firstLetterToUpper

data class Dish(
    val name: String,
    val ingredients: List<String>,
    var portionsLeft: UInt,
    var price: Double,
    var cookingTime: UInt,
    var isOnMenu: Boolean
) {
    override fun toString(): String =
        "${name.firstLetterToUpper()}: ${String.format("%.2f", price)}$\n" +
                "Ingredients:\n- ${ingredients.joinToString(";\n- ")};\n" +
                "Portions: $portionsLeft.\nCooking time: $cookingTime min."
}