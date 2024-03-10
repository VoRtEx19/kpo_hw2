package org.example.database.repository.menu

import org.example.database.connection.DatabaseManager
import java.sql.ResultSet

class DishRepositoryImpl : DishRepository {
    private val databaseManager: DatabaseManager = DatabaseManager.getInstance()

    init {
        databaseManager.performExecute(createDishTableRequest)
    }

    override fun getAll(): List<DishDTO> {
        val list = mutableListOf<DishDTO>()
        val result = databaseManager.performExecuteQuery(getAllDishesRequest)
        while (result?.next() == true) list.add(result.mapToDishDTO())
        return list
    }

    override fun getById(id: String): DishDTO? {
        val result = databaseManager.performExecuteQuery(getDishByNameRequest, id)
        if (result?.next() == true) return result.mapToDishDTO()
        return null
    }

    override fun create(obj: DishDTO): Boolean {
        if (getById(obj.name) != null) return false
        databaseManager.performExecuteUpdate(
            insertDishRequest,
            obj.name,
            obj.ingredients.joinToString(", "),
            obj.portionCount,
            obj.price,
            obj.cookingTime,
            if (obj.isOnMenu) "TRUE" else "FALSE"
        )
        return true
    }

    override fun update(obj: DishDTO): Boolean {
        if (getById(obj.name) == null) return false
        databaseManager.performExecuteUpdate(
            updateDishRequest,
            obj.ingredients.joinToString(", "),
            obj.portionCount,
            obj.price,
            obj.cookingTime,
            if (obj.isOnMenu) "TRUE" else "FALSE",
            obj.name,
        )
        return true
    }

    override fun delete(id: String) {
        databaseManager.performExecuteUpdate(deleteDishRequest, id)
    }

    private fun ResultSet.mapToDishDTO(): DishDTO = DishDTO(
        this.getString(1),
        this.getString(2).split(", "),
        this.getInt(3).toUInt(),
        this.getFloat(4).toDouble(),
        this.getInt(5).toUInt(),
        this.getString(6) == "TRUE"
    )
}