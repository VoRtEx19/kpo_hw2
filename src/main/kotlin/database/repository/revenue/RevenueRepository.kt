package org.example.database.repository.revenue

import org.example.database.repository.RepositoryInterface
import org.example.database.repository.menu.DishDTO

interface RevenueRepository: RepositoryInterface<RevenueDTO, Int> {
    override fun getAll(): List<RevenueDTO>
    override fun getById(id: Int): RevenueDTO?
    override fun create(obj: RevenueDTO): Boolean
    override fun update(obj: RevenueDTO): Boolean
    override fun delete(id: Int)
}