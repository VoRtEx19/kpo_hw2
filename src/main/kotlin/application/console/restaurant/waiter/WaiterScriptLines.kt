package org.example.application.console.restaurant.waiter

/** Script lines for narrator and waiter John connected to waiter serving process */

const val WAITER_INTRO = "Narrator: As you sit down at a table, a young man dressed as a waiter approaches you.\n" +
        "Waiter: Welcome! My name is John, and I will serve you for tonight. What would you like now?\n" +
        "Narrator: Hint: just name what you want to do. If you need help, say \"help\" :)\n"
const val WAITER_OUTRO = "Waiter: It was an honour to serve you tonight! Thank you for coming!\n" +
        "Narrator: You stand up from the table and head to the exit.\n\n"

/** list of commands and their descriptions*/
val WAITER_HELP_COMMANDS = arrayOf(
    "help - If there is anything you need, just ask. I will help.",
    "menu - Just say that, and I'll present the menu to you.",
    "info - and I will tell you all there is to know about the dish.",
    "add - When the order is created, you can say that and add a dish that you would like.",
    "cancel - You cancel your order at any time until it is ready. Then we cannot help it - it's the restaurant's policy.",
    "status - If you wonder when your order will be ready, just ask.",
    "show - If you want me to remind you what you have ordered so far.",
    "order - This will tell me that you are ready to order.",
    "submit - When you think it is enough, I will carry the order to the kitchen so it can start being cooked. You can add new dishes to the order until it is ready.",
    "exit - If you did not find anything interesting, you can leave."
)

/** User lines for commands */
val YOU_COMMAND_LINES = mapOf(
    "help" to "You: Could you help me out? I do not know what to do.\n",
    "menu" to "You: I would like to see the menu, thank you.\n",
    "info" to "You: Could you please tell me more about that dish?\n",
    "add" to "You: I would like to add a dish.\n",
    "cancel" to "You: Excuse me, I will have to cancel my order, sorry. Is this possible?\n",
    "status" to "You: Excuse me, what stage is my order in?\n",
    "show" to "You: Excuse me, could you remind me what is in my order now?\n",
    "order" to "You: Excuse me, I am ready to order.\n",
    "submit" to "You: Yes, that will do it as my order.\n",
    "exit" to "You: I am leaving now, thank you.\n"
)

/** Command results */
const val UNKNOWN_COMMAND = "Waiter: Sorry, what was that?\n"
const val HELP = "Waiter: Sure, here it is:\n"
const val DONE = "Waiter: Yes, sure!\n"
const val ALRIGHT_CANCEL = "Waiter: Alright, I'll give you more time to think.\n"
const val NO_SUCH_ORDER = "Waiter: Sorry, there is no such order.\n"
const val ORDER_READY = "Waiter: Your order is already ready!\n"
const val ORDER_READY_YAY = "Waiter: Your order has been cooked! Enjoy!\n"
const val ORDER_CANCELLED = "Waiter: Wait! You've already cancelled your order. Maybe you want to place a new one?\n"
const val ORDER_RECEIVED = "Waiter: I have already sent this order to the kitchen.\n"
const val ORDER_PAID = "Waiter: This order has already been finished and paid.\n"
const val ORDER_COOKING = "Waiter: This order is already being cooked there in the kitchen.\n"
const val ORDER_CREATING = "Waiter: We are already creating an order.\n"
const val NO_SUCH_DISH_IN_ORDER = "Waiter: You haven't added such dish to your order yet.\n"
const val SORRY_DISHES_FINISHED = "Waiter: I'm sorry, but we have run out of following dishes:\n"
const val DISHES_WERE_REMOVED = "These dishes were removed from your order. You can still order more if you would like.\n"

/** Order statuses */
const val ORDER_STATUS_CREATED = "Waiter: I have your order. What dishes would you like?\n"
const val ORDER_STATUS_RECEIVED = "Waiter: I got it. I'll hand this over to the kitchen.\n"
const val ORDER_STATUS_COOKING = "Waiter: Oh, yes. It is high cooking. It'll be ready in a few minutes.\n"
const val ORDER_STATUS_READY = "Waiter: Your order is ready. Here you go!\n"
const val ORDER_STATUS_CANCELLED = "Waiter: You have cancelled this order.\n"
const val ORDER_STATUS_PAID = "Waiter: You have already paid for this order.\n"
const val ORDER_STATUS_NONE = "Waiter: I think you haven't ordered yet. Care to fix that?\n"

/** Lines for user input */
const val WAITER_REQUEST_DISH = "Waiter: So, what dish would you like? Just name it. If you can't decide, say \"cancel\".\n"
const val WAITER_INVALID_DISH = "Waiter: Sorry, there is no such dish on menu.\n"
const val WAITER_MENU = "Waiter: Here is the menu:\n"
const val EMPTY_MENU = "Waiter: Oh! There is nothing on the menu now.\n"
const val WAITER_ORDER = "Waiter: Your order is as follows:\n"
const val EMPTY_ORDER = "Waiter: You have ordered nothing yet.\n"

/** Payment */
const val CHECK = "Waiter: Here is the check!\n1. Pay\n2. Escape [Dexterity check]\n"