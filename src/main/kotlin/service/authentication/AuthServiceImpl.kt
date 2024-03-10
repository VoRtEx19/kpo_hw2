package org.example.service.authentication

import org.example.database.repository.authentication.UserRepository
import org.example.models.user.User

class AuthServiceImpl(override var userRepository: UserRepository) : AuthService {
    override fun createUser(user: User): Boolean {
        val userData = user.toUserData()
        userData.active = true
        return userRepository.create(userData)
    }

    override fun checkIfExists(user: User): Boolean = userRepository.getById(user.username) != null

    override fun checkPassword(user: User): Boolean {
        val passwordHash = userRepository.getById(user.username)?.passwordHash
        return passwordHash == encryptPassword(user.password)
    }

    override fun changeUsername(user: User, newUsername: String): User? {
        if (newUsername == user.username)
            return user
        if (!checkPassword(user))
            return null
        userRepository.delete(user.username)
        if (!userRepository.create(user.toUserData()))
            return null
        user.username = newUsername
        return user
    }

    override fun changePassword(user: User, newPassword: String): User? {
        if (!checkPassword(user))
            return null
        user.password = newPassword
        if (!userRepository.update(user.toUserData()))
            return null
        return user
    }

    override fun getActivityStatus(user: User): Boolean? = userRepository.getById(user.username)?.active

    override fun setActive(user: User) {
        val userData = userRepository.getById(user.username)
        if (user.toUserData() != userData)
            return
        userData.active = true
        userRepository.update(userData)
    }

    override fun setInactive(user: User) {
        val userData = userRepository.getById(user.username)
        if (encryptPassword(user.password) != userData?.passwordHash)
            return
        userData.active = false
        userRepository.update(userData)
    }

    override fun deleteUser(user: User): Boolean {
        if (!checkPassword(user))
            return false
        userRepository.delete(user.username)
        return true
    }
}