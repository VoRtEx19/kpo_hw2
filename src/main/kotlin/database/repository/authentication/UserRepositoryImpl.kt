package org.example.database.repository.authentication

import org.example.database.connection.DatabaseManager
import java.sql.ResultSet
import java.sql.SQLException

class UserRepositoryImpl : UserRepository {
    private val databaseManager: DatabaseManager = DatabaseManager.getInstance()

    init {
        databaseManager.performExecute(createUserTableRequest)
    }

    override fun getAll(): List<UserDTO> {
        val list = mutableListOf<UserDTO>()
        val result = databaseManager.performExecuteQuery(getAllUsersRequest)
        while (result?.next() == true) list.add(result.mapToUserDTO())
        return list
    }

    override fun getById(id: String): UserDTO? {
        val result = databaseManager.performExecuteQuery(getUserByUsernameRequest, id)
        if (result?.next() == true) return result.mapToUserDTO()
        return null
    }

    override fun create(obj: UserDTO): Boolean {
        if (getById(obj.username) != null) return false
        databaseManager.performExecuteUpdate(
            insertUserRequest, obj.username, obj.passwordHash, when (obj.userRole) {
                UserRole.CLIENT -> "client"
                UserRole.ADMIN -> "admin"
            }, if (obj.active) "TRUE" else "FALSE"
        )
        return true
    }

    override fun update(obj: UserDTO): Boolean {
        if (getById(obj.username) == null) return false
        databaseManager.performExecuteUpdate(
            updateUserRequest, obj.passwordHash, when (obj.userRole) {
                UserRole.CLIENT -> "client"
                UserRole.ADMIN -> "admin"
            }, if (obj.active) "TRUE" else "FALSE", obj.username
        )
        return true
    }

    override fun delete(id: String) {
        databaseManager.performExecuteUpdate(deleteUserRequest, id)
    }

    private fun ResultSet.mapToUserDTO(): UserDTO {
        return UserDTO(
            this.getString(1), this.getString(2), when (this.getString(3)) {
                "client" -> UserRole.CLIENT
                "admin" -> UserRole.ADMIN
                else -> throw SQLException("Corrupted data")
            }, this.getString(4) == "TRUE"
        )
    }
}