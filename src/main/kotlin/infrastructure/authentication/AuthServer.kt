package org.example.infrastructure.authentication

import org.example.models.user.*
import org.example.service.authentication.AuthService

class AuthServer(private val userService: AuthService, private val userInterface: AuthInterface) {
    var currentSession: User = Client("", "")

    private fun signIn(): AuthResult {
        if (!userService.checkIfExists(currentSession))
            return AuthResult.NON_EXISTING_USER
        if (userService.getActivityStatus(currentSession)!!)
            return AuthResult.USER_ALREADY_IN_SESSION
        if (!userService.checkPassword(currentSession))
            return AuthResult.WRONG_PASSWORD
        userService.setActive(currentSession)
        return AuthResult.OK
    }

    private fun signUp(): AuthResult {
        if (userService.checkIfExists(currentSession))
            return AuthResult.ALREADY_EXISTING_USER
        if (!userService.createUser(currentSession))
            return AuthResult.INTERNAL_ERROR
        userService.setActive(currentSession)
        return AuthResult.OK
    }

    fun startSession() {
        userInterface.intro()
        while (true) {
            val authOption = userInterface.requestAuthOption()
            val username = userInterface.requestUsername(authOption)
            val password = userInterface.requestPassword(authOption)

            currentSession = when (userInterface.requestRole()) {
                UserRole.CLIENT -> Client(username, password)
                UserRole.ADMIN -> Admin(username, password)
            }

            val authResult: AuthResult = when (authOption) {
                AuthOption.SIGN_IN -> signIn()
                AuthOption.SIGN_UP -> signUp()
            }
            userInterface.displayResult(authResult)
            if (authResult == AuthResult.OK)
                break
        }
    }

    fun closeSession() {
        userService.setInactive(currentSession)
        userInterface.outro()
    }
}