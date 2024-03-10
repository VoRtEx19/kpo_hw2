package org.example.database.repository.menu

import org.example.database.repository.RepositoryInterface

interface DishRepository : RepositoryInterface<DishDTO, String> {
    override fun getAll(): List<DishDTO>
    override fun getById(id: String): DishDTO?
    override fun create(obj: DishDTO): Boolean
    override fun update(obj: DishDTO): Boolean
    override fun delete(id: String)
}