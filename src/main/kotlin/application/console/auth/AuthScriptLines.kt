package org.example.application.console.auth

/** Script lines for narrator and Maitre de Restaurant connected to authentication process */

/** Narrator */
const val INTRO = "\nMaître de Restaurant: Hello! And welcome to KPO Restaurant!\nYou: Greetings!\n"
const val OUTRO =
    "Maître de Restaurant: Goodbye! Hope I will see you again soon!\nNarrator: You leave the restaurant. Its doors close gently behind you.\n\n"

const val FAIL = "Narrator: You have failed to enter the restaurant. But don't despair yet! You can try again.\n\n"
const val SUCCESS = "Narrator: Congratulations! Now you are inside.\n\n"

/** Maitre */
const val WELCOME = "Maître de Restaurant: Welcome to the restaurant!\n"

const val USER_DOES_NOT_EXIST = "Maître de Restaurant: Sorry, I don't know anyone who is called like that.\n"
const val USER_ALREADY_EXISTS =
    "Maître de Restaurant: Sorry, there is already somebody called like that, and I'm sure that it's not you.\n"

const val USER_ALREADY_IN =
    "Maître de Restaurant: Sorry, but you are already inside. I cannot let you in until you leave.\n"
const val USER_ALREADY_OUT = "Maître de Restaurant: Sorry, but first you will have to come inside.\n"

const val WRONG_PASSWORD = "Maître de Restaurant: I don't think that's the right password.\n"
const val SOMETHING_WRONG =
    "Maître de Restaurant: Sorry, my notes are all messed up. Please, come again later when I've sorted it all through.\n"

const val AUTH_OPTION_REQUEST =
    "Maître de Restaurant: Are you new here or have you been here before?\n1. I am new here.\n2. I have been here before.\n"

const val NAME_REQUEST_FIRST_TIME =
    "Maître de Restaurant: Tell me your name so that I can recognize you by it in the future.\n"
const val NAME_REQUEST = "Maître de Restaurant: May I have your name?\n"

const val PASSWORD_REQUEST_FIRST_TIME =
    "Maître de Restaurant: Now tell me a secret word by which I will know it is you in the future.\n"
const val PASSWORD_REQUEST = "Maître de Restaurant: Now tell me a secret word so that I can let you in.\n"

const val ROLE_REQUEST =
    "Maître de Restaurant: And the last question, do you work here or are you a guest?\n1. I am a client.\n2. I am an admin.\n"