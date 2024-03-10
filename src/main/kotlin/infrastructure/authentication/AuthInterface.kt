package org.example.infrastructure.authentication

interface AuthInterface {
    fun intro()
    fun outro()
    fun displayResult(result: AuthResult)
    fun requestAuthOption() : AuthOption
    fun requestUsername(authOption: AuthOption) : String
    fun requestPassword(authOption: AuthOption) : String
    fun requestRole() : UserRole
}