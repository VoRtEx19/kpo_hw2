package org.example.service.authentication

import org.example.database.repository.authentication.UserDTO
import org.example.database.repository.authentication.UserRole
import org.example.models.user.*
import java.security.MessageDigest
import java.util.*

fun User.toUserData(): UserDTO = UserDTO(username,
    encryptPassword(password), when (this) {
        is Client -> UserRole.CLIENT
        is Admin -> UserRole.ADMIN
        else -> throw IllegalArgumentException("Unknown user type")
    }, false
)

fun encryptPassword(password: String): String {
    val bytes = password.toByteArray()
    val md = MessageDigest.getInstance("SHA-256")
    val digest = md.digest(bytes)

    return Base64.getEncoder().encodeToString(digest)
}