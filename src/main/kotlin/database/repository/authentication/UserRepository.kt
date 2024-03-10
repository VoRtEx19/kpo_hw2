package org.example.database.repository.authentication

import org.example.database.repository.RepositoryInterface

interface UserRepository: RepositoryInterface<UserDTO, String> {
    override fun getAll(): List<UserDTO>
    override fun getById(id: String): UserDTO?
    override fun create(obj: UserDTO): Boolean
    override fun update(obj: UserDTO): Boolean
    override fun delete(id: String)
}