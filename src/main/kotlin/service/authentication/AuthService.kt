package org.example.service.authentication

import org.example.database.repository.authentication.UserRepository
import org.example.models.user.User

interface AuthService {
    var userRepository: UserRepository

    fun createUser(user: User): Boolean
    fun checkIfExists(user: User): Boolean
    fun checkPassword(user: User): Boolean
    fun changeUsername(user: User, newUsername: String): User?
    fun changePassword(user: User, newPassword: String): User?
    fun getActivityStatus(user: User): Boolean?
    fun setActive(user: User)
    fun setInactive(user: User)
    fun deleteUser(user: User): Boolean
}