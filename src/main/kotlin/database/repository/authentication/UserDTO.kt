package org.example.database.repository.authentication

data class UserDTO(
    var username: String,
    var passwordHash: String,
    var userRole: UserRole,
    var active: Boolean
)
