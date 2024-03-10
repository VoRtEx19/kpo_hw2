package org.example.infrastructure.authentication

enum class AuthResult {
    NON_EXISTING_USER,
    ALREADY_EXISTING_USER,
    USER_ALREADY_IN_SESSION,
    USER_NOT_IN_SESSION,
    WRONG_PASSWORD,
    OK,
    INTERNAL_ERROR
}