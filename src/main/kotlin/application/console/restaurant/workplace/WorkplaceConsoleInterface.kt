package org.example.application.console.restaurant.workplace

import org.example.application.console.*
import org.example.infrastructure.restaurant.commands.Command
import org.example.infrastructure.restaurant.commands.admin.EditDishCommand.EditOption
import org.example.infrastructure.restaurant.system.interfaces.WorkplaceInterface
import org.example.infrastructure.restaurant.system.results.WorkplaceResult
import org.example.models.dish.Dish
import java.util.Locale

/**
 * Console implementation of [WorkplaceInterface]
 *
 * It implements methods of [WorkplaceInterface] in a console version of a management system PC
 *
 * @see WorkplaceInterface
 */
class WorkplaceConsoleInterface : WorkplaceInterface {

    /**
     * Displays the introduction-line from system PC
     */
    override fun intro() {
        print(WORKPLACE_INTRO)
    }

    /**
     * Displays the goodbye-line from system PC
     */
    override fun outro() {
        print(WORKPLACE_OUTRO)
    }

    /**
     * Displays list of commands available for admin with waiter John's remarks about what they do
     *
     * @see Command
     */
    override fun displayHelp() {
        WORKPLACE_HELP_COMMANDS.forEach { println(it) }
    }

    /**
     * Displays list <dish: whether on menu or not> for each dish that is in the database
     *
     * @see Dish
     */
    override fun displayMenu(menu: List<Dish>) {
        if (menu.isEmpty()) {
            print(EMPTY_MENU)
            return
        }
        println(WORKPLACE_MENU)
        menu.forEach { println("- ${it.name.firstLetterToUpper()}: ${if (it.isOnMenu) "on menu" else "off menu"}") }
        println()
    }

    /**
     * Displays full info for a certain dish
     * If user has cancelled its choice, and the dish is null, displays corresponding response from system PC
     *
     * @see Dish
     */
    override fun displayDishInfo(dish: Dish?) {
        println(dish?.toString()?.plus("\nOn menu: ${if (dish.isOnMenu) "yes" else "no"}\n") ?: RESULT_CANCELLED)
    }

    /**
     * Displays computer response depending on the [workplaceResult] passed.
     * The [workplaceResult] is taken from commands
     *
     * @see Command
     */
    override fun displayResult(workplaceResult: WorkplaceResult) {
        print(
            when (workplaceResult) {
                WorkplaceResult.DISH_ALREADY_EXISTS -> DISH_ALREADY_EXISTS
                WorkplaceResult.DISH_ALREADY_ON_MENU -> DISH_ALREADY_ON_MENU
                WorkplaceResult.NON_EXISTING_DISH -> DISH_NOT_EXISTS
                WorkplaceResult.OK -> RESULT_OK
                WorkplaceResult.CANCELLED -> RESULT_CANCELLED
                WorkplaceResult.DISH_ALREADY_OFF_MENU -> DISH_ALREADY_OFF_MENU
            }
        )
    }

    override fun displayRevenue(revenue: UInt) {
        print("Total revenue: $revenue")
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
            if (input in commandList) break
            else print(INVALID_INPUT)
        }
        return input
    }

    /**
     * Gets a new dish from user, requiring input for corresponding properties using util functions [getString], [getUInt]
     * NB: Dish name is stored in lowercase
     *
     * @return a dish to be created
     *
     * @see getString
     * @see getUInt
     */
    override fun getNewDish(): Dish {
        print(DISH_CREATION_STARTED)
        return Dish(
            name = getString(REQUEST_NAME, INPUT, INVALID_INPUT).lowercase(Locale.getDefault()),
            ingredients = getStringList(REQUEST_INGREDIENTS, INPUT, INVALID_INPUT_MUST_CONTAIN_ONLY_LETTERS),
            portionsLeft = getUInt(REQUEST_POTION_COUNT, INPUT, INVALID_INPUT) ?: 0U,
            price = getDouble(
                REQUEST_PRICE,
                INPUT,
                INVALID_INPUT,
                lowerBound = 0.0,
                inputOutOfBounds = WORKPLACE_INPUT_OUT_OF_BOUNDS
            ) ?: 0.0,
            cookingTime = getUInt(REQUEST_COOKING_TIME, INPUT, INVALID_INPUT) ?: 0U,
            isOnMenu = false
        )
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
            val input = getString(WORKPLACE_REQUEST_DISH, INPUT, INVALID_INPUT).lowercase(Locale.getDefault())
            if (input == "cancel") return null
            dish = menu.find { it.name == input }
        } while (if (dish == null) {
                print(WORKPLACE_INVALID_DISH)
                true
            } else false
        )
        return dish!!
    }

    /**
     * Gets dish edit option (price, portions left, cooking time) using util function [getUInt]
     *
     * @return edit option
     *
     * @throws RuntimeException if result returns out of bounds which is practically impossible
     *
     * @see EditOption
     * @see getUInt
     */
    override fun getEditOption(): EditOption = when (
        getUInt(
            EDIT_OPTION_REQUEST,
            inputRequest = INPUT,
            lowerBound = 1U,
            upperBound = 3U,
            invalidInput = INVALID_INPUT,
            inputOutOfBounds = INPUT_OUT_OF_BOUNDS
        )
    ) {
        1U -> EditOption.PRICE
        2U -> EditOption.COOKING_TIME
        3U -> EditOption.PORTION_COUNT
        else -> throw RuntimeException("Usually this is not possible, but we got out of bounds! in getEditOption")
    }

    /**
     * Gets new value for the dish with line depending on [editOption], using [getUInt]
     *
     * @return new value or null if cancelled
     *
     * @see EditOption
     * @see getUInt
     */
    override fun getNewUIntValue(editOption: EditOption): UInt? = getUInt(
        when (editOption) {
            EditOption.PORTION_COUNT -> REQUEST_NEW_PORTION_COUNT
            EditOption.COOKING_TIME -> REQUEST_NEW_COOKING_TIME
            else -> throw IllegalArgumentException("Cannot get UInt value for double property! in getNewUIntValue")
        },
        inputRequest = INPUT,
        invalidInput = INVALID_INPUT,
        cancellationEnable = true
    )

    /**
     * Gets new value for the dish price, using [getDouble]
     *
     * @return new value or null if cancelled
     *
     * @see getDouble
     */
    override fun getNewDoubleValue(): Double? = getDouble(
        REQUEST_PRICE,
        inputRequest = INPUT,
        invalidInput = INVALID_INPUT,
        lowerBound = 0.0,
        inputOutOfBounds = WORKPLACE_INPUT_OUT_OF_BOUNDS,
        cancellationEnable = true
    )
}