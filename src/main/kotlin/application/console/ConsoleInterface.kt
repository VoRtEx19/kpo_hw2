package org.example.application.console

import org.example.application.AppInterface
import org.example.application.console.auth.AuthConsoleInterface
import org.example.application.console.restaurant.waiter.WaiterConsoleInterface
import org.example.application.console.restaurant.workplace.WorkplaceConsoleInterface

/**
 * Aggregator class for console interfaces (implements [AppInterface])
 *
 * @property authInterface has interface used for authentication
 * @property waiterInterface has interface used as a waiter for communicating with client
 * @property workplaceInterface has interface used as a management system PC for communicating with admin
 *
 * @see AppInterface
 * @see AuthConsoleInterface
 * @see WaiterConsoleInterface
 * @see WorkplaceConsoleInterface
 */
class ConsoleInterface : AppInterface() {
    override val authInterface = AuthConsoleInterface()
    override val waiterInterface = WaiterConsoleInterface()
    override val workplaceInterface = WorkplaceConsoleInterface()

    /**
     * Displays introduction-line for starting the app
     */
    override fun intro() {
        print(INTRO)
    }

    /**
     * Displays goodbye-line when finishing the app
     */
    override fun outro() {
        print(OUTRO)
    }

    /**
     * Function for getting user choice whether to approach the restaurant and start the app or leave
     *
     * @return user choice: true for start, false for exit
     *
     * @see getUInt
     */
    override fun ready(): Boolean =
        getUInt("", inputRequest = YOU_HEADER, lowerBound = 1U, upperBound = 2U) == 1U

    /**
     * Function that displays if there was an error executing the app, and what kind of error it was
     */
    override fun displayException(e: Exception) {
        println(e.message)
    }
}