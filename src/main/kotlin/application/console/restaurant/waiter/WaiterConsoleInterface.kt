package org.example.application.console.restaurant.waiter

import org.example.application.console.*
import org.example.infrastructure.restaurant.system.interfaces.WaiterInterface
import org.example.infrastructure.restaurant.system.results.WaiterResult
import org.example.models.dish.Dish
import org.example.models.order.OrderStatus
import org.example.infrastructure.restaurant.commands.Command
import org.example.models.order.Order
import java.util.Locale
import kotlin.random.Random

/**
 * Console implementation of [WaiterInterface]
 *
 * It implements methods of [WaiterInterface] in a console version of a waiter John - the person who serves people at
 * a table in a restaurant.
 *
 * @see WaiterInterface
 */
class WaiterConsoleInterface : WaiterInterface {
    /**
     * Displays the introduction-line from waiter John
     */
    override fun intro() {
        print(WAITER_INTRO)
    }

    /**
     * Displays the goodbye-line from waiter John
     */
    override fun outro() {
        print(WAITER_OUTRO)
    }

    /**
     * Displays list of commands available for client with waiter John's remarks about what they do
     *
     * @see Command
     */
    override fun displayHelp() {
        print(HELP)
        WAITER_HELP_COMMANDS.forEach { println(it) }
    }

    /**
     * Displays list <dish: $price> for each dish that is available on menu
     *
     * @see Dish
     */
    override fun displayMenu(menu: List<Dish>) {
        if (menu.isEmpty()) {
            print(EMPTY_MENU)
            return
        }
        print(WAITER_MENU)
        menu.forEach { println(" - ${it.name.firstLetterToUpper()}: $${String.format("%.2f", it.price)}") }
        println()
    }

    /**
     * Displays list <dish: $price> for each dish in order as well as the total price
     */
    override fun displayOrder(order: Order?) {
        if (order == null) {
            print(ORDER_STATUS_NONE)
            return
        }
        if (order.dishes.isEmpty()) {
            print(EMPTY_ORDER)
            return
        }
        print(WAITER_ORDER)
        order.dishes.forEach { println(" - ${it.name.firstLetterToUpper()}: $${String.format("%.2f", it.price)}") }
        print("Total cost: ${order.getTotalPrice()}\n")
    }

    /**
     * Displays info which dishes have finished and that they have been removed
     * He can still edit his order, it has not been submitted
     */
    override fun displayDishesThatFinished(dishes: List<Dish>) {
        dishes.forEach { println(" - ${it.name.firstLetterToUpper()}: $${String.format("%.2f", it.price)}") }
        print(DISHES_WERE_REMOVED)
    }

    /**
     * Displays full info for a certain dish
     * If user has cancelled its choice, and the dish is null, displays corresponding response from waiter John
     *
     * @see Dish
     */
    override fun displayDishInfo(dish: Dish?) {
        println(dish?.toString()?.plus("\n") ?: ALRIGHT_CANCEL)
    }

    /**
     * Displays lines from waiter John depending on the [waiterResult] passed.
     * The [waiterResult] is taken from commands
     *
     * @see Command
     */
    override fun displayResult(waiterResult: WaiterResult) {
        print(
            when (waiterResult) {
                WaiterResult.OK -> DONE
                WaiterResult.DISH_CHOICE_CANCELLED -> ALRIGHT_CANCEL
                WaiterResult.ORDER_CREATING -> ORDER_CREATING
                WaiterResult.ORDER_NOT_EXISTS -> NO_SUCH_ORDER
                WaiterResult.ORDER_ALREADY_READY -> ORDER_READY
                WaiterResult.ORDER_CANCELLED -> ORDER_CANCELLED
                WaiterResult.ORDER_RECEIVED -> ORDER_RECEIVED
                WaiterResult.ORDER_PAID -> ORDER_PAID
                WaiterResult.ORDER_COOKING -> ORDER_COOKING
                WaiterResult.NO_SUCH_DISH_IN_ORDER -> NO_SUCH_DISH_IN_ORDER
                WaiterResult.INSUFFICIENT_AMOUNT_DISHES -> SORRY_DISHES_FINISHED
            }
        )
    }

    /**
     * Displays [status] of user's order
     * [status] is null if it has not yet been created
     * @see OrderStatus
     */
    override fun displayOrderStatus(status: OrderStatus?) {
        print(
            when (status) {
                OrderStatus.CREATING -> ORDER_STATUS_CREATED
                OrderStatus.RECEIVED -> ORDER_STATUS_RECEIVED
                OrderStatus.COOKING -> ORDER_STATUS_COOKING
                OrderStatus.READY -> ORDER_STATUS_READY
                OrderStatus.CANCELLED -> ORDER_STATUS_CANCELLED
                OrderStatus.PAID -> ORDER_STATUS_PAID
                null -> ORDER_STATUS_NONE
            }
        )
    }

    /**
     * Notifies user that order is ready
     */
    override fun orderReady() {
        print(ORDER_READY_YAY)
    }

    /**
     * Gets command name from user
     * Command must be present in [commandList] - list of commands available for user
     *
     * @return command name
     *
     * @see Command
     */
    override fun getCommand(commandList: List<String>): String {
        var input: String
        while (true) {
            print(COMMAND_HEADER)
            input = readln()
            print(YOU_COMMAND_LINES[input] ?: UNKNOWN_COMMAND)
            if (input in commandList) break
        }
        return input
    }

    /**
     * Gets a dish that user can select from the [menu] using [getString]
     *
     * @return dish gathered from name user had entered
     *
     * @see getString
     */
    override fun getDish(menu: List<Dish>): Dish? {
        var dish: Dish?
        do {
            val input = getString(WAITER_REQUEST_DISH).lowercase(Locale.getDefault())
            if (input == "cancel") return null
            dish = menu.find { it.name == input }
        } while (if (dish == null) {
                print(WAITER_INVALID_DISH)
                true
            } else false
        )
        return dish!!
    }
}