package org.example.application.console.restaurant.workplace

/** Script lines for management system aka workplace, connected to admin work */

const val WORKPLACE_INTRO = "PC: Welcome to Restaurant Management System! (To get started, type \"help\")\n"
const val WORKPLACE_OUTRO = "PC: System shutdown.\n\n"

/** list of commands and their descriptions*/
val WORKPLACE_HELP_COMMANDS = arrayOf(
    "help - display list of all available commands",
    "menu - display list of all dishes (admin version)",
    "info - full information about a dish",
    "create - create a new dish",
    "add - add an existing dish to menu",
    "edit - edit existing dish",
    "remove - remove dish from menu",
    "revenue - see total revenue of the restaurant",
    "exit - turn off pc"
)

/** Input connected lines */
const val INPUT = "Input >> "
const val INVALID_INPUT = "PC: Error: Invalid input.\n"
const val INVALID_INPUT_MUST_CONTAIN_ONLY_LETTERS = "PC: Error: Invalid input - must contain letters only.\n"
const val WORKPLACE_INPUT_OUT_OF_BOUNDS = "PC: Error: Input out of bounds.\n"
const val WORKPLACE_REQUEST_DISH = "PC: Select dish from the menu by its name. To cancel enter \"cancel\".\n"
const val WORKPLACE_INVALID_DISH = "PC: There is no such dish.\n"

/** Menu connected lines */
const val WORKPLACE_MENU = "PC: Dish list:"
const val EMPTY_MENU = "Nothing here.\n"

/** Dish creation connected lines */
const val DISH_CREATION_STARTED = "\nPC: Dish creation has started. \n" +
        "Now you will be required to input values for the dish.\n" +
        "It will not be added to menu automatically, only to assortment.\n\n"
const val REQUEST_NAME = "PC: Enter dish name.\n"
const val REQUEST_INGREDIENTS = "PC: Enter ingredients each on separate line. To end, enter empty string.\n"
const val REQUEST_POTION_COUNT = "PC: Enter number of portions available.\n"
const val REQUEST_PRICE = "PC: Enter dish price (in $).\n"
const val REQUEST_COOKING_TIME = "PC: Enter time required to cook dish (in minutes).\n"

/** Dish editing connected lines */
const val EDIT_OPTION_REQUEST = "PC: Choose edited property:\n1. Price\n2. Cooking time\n3. Portion count\n"
const val REQUEST_NEW_PRICE = "PC: Enter new price.\n"
const val REQUEST_NEW_PORTION_COUNT = "PC: Enter new portion count.\n"
const val REQUEST_NEW_COOKING_TIME = "PC: Enter new cooking time.\n"

/** Command execution result lines */
const val RESULT_OK = "PC: Command successfully executed.\n\n"
const val RESULT_CANCELLED = "PC: Command cancelled.\n\n"
const val DISH_ALREADY_EXISTS = "PC: Such dish already exists. Try another one.\n\n"
const val DISH_NOT_EXISTS = "PC: Such dish does not exist. Try another one.\n\n"
const val DISH_ALREADY_ON_MENU = "PC: Such dish is already on menu. Try another one.\n\n"
const val DISH_ALREADY_OFF_MENU = "PC: Such dish is already not on menu. Try another one.\n\n"
