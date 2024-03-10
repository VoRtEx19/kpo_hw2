package org.example.application.console

import java.util.*

/** Util functions for getting input from user. Their style is python-like, I know */

/**
 * Function for getting a string from user
 *
 * First prints [prompt],
 * then with each attempt [inputRequest]
 * If user inputs empty string, it is not tolerated [invalidInput]
 *
 * @return non-empty string
 */
fun getString(prompt: String, inputRequest: String = YOU_HEADER, invalidInput: String = EMPTY_INPUT): String {
    print(prompt)
    var input: String
    do {
        print(inputRequest)
        input = readln()
    } while (if (input.isEmpty()) {
            print(invalidInput)
            true
        } else false
    )
    return input
}

/**
 * Function for getting a list of strings from user
 * Input finishes when empty string is input
 * If string does not consist of letters only it is an invalid input, but reading goes on
 *
 * First prints [prompt],
 * then with each input attempt [inputRequest].
 * If user inputs something non-literal, displays [invalidInput], but goes on reading
 *
 * @return mutable list of letter-only strings
 */
fun getStringList(
    prompt: String, inputRequest: String = YOU_HEADER, invalidInput: String = UNRECOGNIZABLE_INPUT
): List<String> {
    print(prompt)
    val result: MutableList<String> = mutableListOf()
    var input = ""
    do {
        if (input.isNotEmpty() && !input.all { it.isLetter() || it == ' ' }) {
            print(invalidInput)
        } else if (input.isNotEmpty()) result.add(input)
        print(inputRequest)
        input = readln()
    } while (input.isNotEmpty())
    return result
}

/**
 * Function for getting a UInt number
 *
 * First prints [prompt],
 * then with each input attempt [inputRequest].
 * If user inputs invalid input, displays [invalidInput]
 * If input is not between [lowerBound] and [upperBound] (including), displays [inputOutOfBounds]
 * If [cancellationEnable], will accept "cancel" and return null
 *
 * @return a UInt number within bounds, or null if cancellation was enabled
 */
fun getUInt(
    prompt: String,
    inputRequest: String = YOU_HEADER,
    invalidInput: String = UNRECOGNIZABLE_INPUT,
    lowerBound: UInt = 0U,
    upperBound: UInt = UInt.MAX_VALUE,
    inputOutOfBounds: String = INPUT_OUT_OF_BOUNDS,
    cancellationEnable: Boolean = false
): UInt? {
    print(prompt)
    var result: UInt? = null
    do {
        try {
            print(inputRequest)
            val input = readln()
            if (cancellationEnable && input == "cancel") return null
            result = input.toUInt()
            if (result !in lowerBound..upperBound) {
                print(inputOutOfBounds)
                continue
            }
        } catch (_: NumberFormatException) {
            print(invalidInput)
        }
    } while (result == null)
    return result
}

/**
 * Function for getting a Double number
 *
 * First prints [prompt],
 * then with each input attempt [inputRequest].
 * If user inputs invalid input, displays [invalidInput]
 * If input is not between [lowerBound] and [upperBound] (including), displays [inputOutOfBounds]
 * If [cancellationEnable], will accept "cancel" and return null
 *
 * @return a Double number within bounds, or null if cancellation was enabled
 */
fun getDouble(
    prompt: String,
    inputRequest: String = YOU_HEADER,
    invalidInput: String = UNRECOGNIZABLE_INPUT,
    lowerBound: Double = Double.MIN_VALUE,
    upperBound: Double = Double.MAX_VALUE,
    inputOutOfBounds: String = INPUT_OUT_OF_BOUNDS,
    cancellationEnable: Boolean = false
): Double? {
    print(prompt)
    var result: Double? = null
    do {
        try {
            print(inputRequest)
            val input = readln()
            if (cancellationEnable && input == "cancel") return null
            result = input.toDouble()
            if (result !in lowerBound..upperBound) {
                print(inputOutOfBounds)
                continue
            }
        } catch (_: NumberFormatException) {
            print(invalidInput)
        }
    } while (result == null)
    return result
}

/** as well as a function for editing string */

/**
 * Function for editing [this] string so that its first letter becomes upper case while everything else - lower case
 */
fun String.firstLetterToUpper(): String =
    this.substring(0, 1).uppercase(Locale.getDefault()) + this.substring(1).lowercase(Locale.getDefault())