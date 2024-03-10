package org.example.infrastructure.restaurant.system.results

enum class WaiterResult {
    OK,
    DISH_CHOICE_CANCELLED,
    NO_SUCH_DISH_IN_ORDER,
    INSUFFICIENT_AMOUNT_DISHES,
    ORDER_CREATING,
    ORDER_RECEIVED,
    ORDER_PAID,
    ORDER_COOKING,
    ORDER_NOT_EXISTS,
    ORDER_ALREADY_READY,
    ORDER_CANCELLED
}