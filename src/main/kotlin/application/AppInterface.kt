package org.example.application

import org.example.infrastructure.authentication.AuthInterface
import org.example.infrastructure.restaurant.system.interfaces.WaiterInterface
import org.example.infrastructure.restaurant.system.interfaces.WorkplaceInterface

/**
 * Abstract class that makes a template for an app interface
 *
 * @property authInterface - interface for authentication
 * @property waiterInterface - interface for waiter
 * @property workplaceInterface - interface for workplace
 */
abstract class AppInterface {

    abstract val authInterface: AuthInterface
    abstract val waiterInterface: WaiterInterface
    abstract val workplaceInterface: WorkplaceInterface

    /**
     * Function that displays introduction when app is launched
     */
    abstract fun intro()

    /**
     * Function that displays finishing lines of the app
     */
    abstract fun outro()

    /**
     * Function that gets whether user is ready to start or ready to leave
     */
    abstract fun ready(): Boolean

    /**
     * Function that displays if there was an error executing the app, and what kind of error it was
     */
    abstract fun displayException(e: Exception)
}