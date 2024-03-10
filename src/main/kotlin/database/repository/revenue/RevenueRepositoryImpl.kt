package org.example.database.repository.revenue

import org.example.database.connection.DatabaseManager
import java.sql.ResultSet

class RevenueRepositoryImpl : RevenueRepository {
    private val databaseManager: DatabaseManager = DatabaseManager.getInstance()

    init {
        databaseManager.performExecute(createRevenueTableRequest)
    }

    override fun getAll(): List<RevenueDTO> {
        val list = mutableListOf<RevenueDTO>()
        val result = databaseManager.performExecuteQuery(getAllRevenueRequest)
        while (result?.next() == true) list.add(result.mapToRevenueDTO())
        return list
    }

    override fun getById(id: Int): RevenueDTO? {
        val result = databaseManager.performExecuteQuery(getRevenueByIdRequest, id)
        if (result?.next() == true) return result.mapToRevenueDTO()
        return null
    }

    override fun create(obj: RevenueDTO): Boolean {
        databaseManager.performExecuteUpdate(
            insertRevenueRequest, obj.revenue
        )
        return true
    }

    override fun update(obj: RevenueDTO): Boolean {
        databaseManager.performExecuteUpdate(
            updateRevenueRequest, obj.revenue
        )
        return true
    }

    override fun delete(id: Int) {
        databaseManager.performExecuteUpdate(deleteRevenueRequest)
    }

    private fun ResultSet.mapToRevenueDTO(): RevenueDTO = RevenueDTO(this.getInt(1).toUInt())

}