package org.example.application.console.auth

import org.example.application.console.*
import org.example.infrastructure.authentication.*
import org.example.infrastructure.restaurant.commands.Command

import java.util.Locale

/**
 * Console implementation of [AuthInterface]
 *
 * It implements methods of [AuthInterface] in a console version of Maitre de Restaurant - the person who stands at the
 * entrance of a restaurant. In this case logic is that they serve as an authentication guard [AuthServer].
 *
 * @see AuthInterface
 */
class AuthConsoleInterface : AuthInterface {
    /**
     * Displays the introduction-line from Maitre de Restaurant
     */
    override fun intro() {
        print(INTRO)
    }

    /**
     * Displays the goodbye-line from Maitre de Restaurant
     */
    override fun outro() {
        print(OUTRO)
    }

    /**
     * Displays lines from Maitre de Restaurant depending on the [result] passed.
     * The [result] is taken from commands
     *
     * @see Command
     */
    override fun displayResult(result: AuthResult) {
        print(
            when (result) {
                AuthResult.NON_EXISTING_USER -> USER_DOES_NOT_EXIST
                AuthResult.ALREADY_EXISTING_USER -> USER_ALREADY_EXISTS
                AuthResult.USER_ALREADY_IN_SESSION -> USER_ALREADY_IN
                AuthResult.USER_NOT_IN_SESSION -> USER_ALREADY_OUT
                AuthResult.WRONG_PASSWORD -> WRONG_PASSWORD
                AuthResult.OK -> WELCOME
                AuthResult.INTERNAL_ERROR -> SOMETHING_WRONG
            }
        )
        print(if (result == AuthResult.OK) SUCCESS else FAIL)
    }

    /**
     * Interface requests user whether they would like to sign in or sign up
     *
     * @return the option
     *
     * @see AuthOption
     */
    override fun requestAuthOption(): AuthOption {
        print(AUTH_OPTION_REQUEST)
        while (true) {
            print(YOU_HEADER)
            return when (readln()) {
                "1" -> AuthOption.SIGN_UP
                "2" -> AuthOption.SIGN_IN
                else -> {
                    print(UNRECOGNIZABLE_INPUT)
                    continue
                }
            }
        }
    }

    /**
     * Interface requests username using util function [getString]
     * Lines vary depending on [authOption]
     *
     * @return username in lower case (system is not case-sensitive)
     *
     * @see getString
     */
    override fun requestUsername(authOption: AuthOption): String = getString(
        when (authOption) {
            AuthOption.SIGN_IN -> NAME_REQUEST
            AuthOption.SIGN_UP -> NAME_REQUEST_FIRST_TIME
        }
    ).lowercase(Locale.getDefault())

    /**
     * Interface requests password using util function [getString]
     * Lines vary depending on [authOption]
     *
     * @return password entered by user
     *
     * @see getString
     */
    override fun requestPassword(authOption: AuthOption): String = getString(
        when (authOption) {
            AuthOption.SIGN_IN -> PASSWORD_REQUEST
            AuthOption.SIGN_UP -> PASSWORD_REQUEST_FIRST_TIME
        }
    )

    /**
     * Interface requests user role (client or admin) using util function [getUInt]
     *
     * @return user role
     *
     * @throws RuntimeException if result returns out of bounds which is practically impossible
     *
     * @see UserRole
     * @see getUInt
     */
    override fun requestRole(): UserRole = when (getUInt(ROLE_REQUEST, lowerBound = 1U, upperBound = 2U)) {
        1U -> UserRole.CLIENT
        2U -> UserRole.ADMIN
        else -> {
            throw RuntimeException("Usually this is not possible, but we got out of bounds! in requestRole")
        }
    }
}