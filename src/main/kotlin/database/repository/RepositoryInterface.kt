package org.example.database.repository


interface RepositoryInterface<T, U> {
    fun getAll(): List<T>
    fun getById(id: U): T?
    fun create(obj: T): Boolean
    fun update(obj: T): Boolean
    fun delete(id: U)
}