package org.example.application

import org.example.infrastructure.authentication.AuthServer
import org.example.infrastructure.restaurant.kitchen.Kitchen
import org.example.infrastructure.restaurant.system.entities.*
import org.example.models.user.*

/**
 * Class that is the application
 *
 * @property appInterface - the interface of the application
 * @property authServer - changeable in configuration, sets an auth server for the application
 * @property kitchen - changeable in configuration, sets a kitchen (order execution)
 * @property system - lateinit so that when the user role is defined,
 * it is clear which type will serve them - [Waiter] or [Workplace]
 */
class Application(val appInterface: AppInterface) {
    var authServer: AuthServer? = null
    var kitchen: Kitchen? = null
    lateinit var system: System

    /**
     * Function that executes the app
     * 1. Checks if the app is not configured
     */
    suspend fun launch() {
        try {
            if (null in arrayOf(authServer, kitchen))
                throw RuntimeException("You must configure the app first!")

            while (true) {
                appInterface.intro()
                if (!appInterface.ready())
                    break

                authServer!!.startSession()

                system = when (authServer!!.currentSession) {
                    is Client -> Waiter(authServer!!.currentSession as Client, kitchen!!, appInterface.waiterInterface)
                    is Admin -> Workplace(
                        authServer!!.currentSession as Admin,
                        kitchen!!,
                        appInterface.workplaceInterface
                    )

                    else -> throw RuntimeException("Something went wrong. Unknown user type.")
                }

                system.serveUser()

                authServer!!.closeSession()
            }

            appInterface.outro()
        } catch (e: Exception) {
            appInterface.displayException(e)
        }
    }
}