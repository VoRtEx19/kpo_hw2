package org.example.database.connection

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.util.*

class DatabaseManager private constructor() {
    companion object {
        private const val URL_KEY: String = "db.url"
        private const val DB_USERNAME: String = "db.username"
        private const val DB_PASSWORD: String = "db.password"

        @Volatile
        private var instance: DatabaseManager? = null

        fun getInstance() = instance ?: synchronized(this) {
            instance ?: DatabaseManager().also { instance = it }
        }

    }

    private val properties = Properties()

    init {
        with(properties) {
            put("user", PropertiesUtil.get(DB_USERNAME))
            put("password", PropertiesUtil.get(DB_PASSWORD))
        }
    }

    private fun getConnection(): Connection = DriverManager.getConnection(PropertiesUtil.get(URL_KEY), properties)

    fun performExecute(sql: String, vararg args: Any): Boolean {
        val c = getConnection()
        try {
            c.use {
                val query = getPreparedQuery(c, sql, *args)
                return query.execute()
            }

        } catch (e: Exception) {
            c.close()
            throw e
        }
    }

    fun performExecuteQuery(sql: String, vararg args: Any): ResultSet? {
        val c = getConnection()
        try {
            c.use {
                val query = getPreparedQuery(c, sql, *args)
                return query.executeQuery()
            }

        } catch (e: Exception) {
            c.close()
            throw e
        }
    }

    fun performExecuteUpdate(sql: String, vararg args: Any): Int {
        val c = getConnection()
        try {
            c.use {
                val query = getPreparedQuery(c, sql, *args)
                return query.executeUpdate()
            }

        } catch (e: Exception) {
            c.close()
            throw e
        }
    }

    private fun getPreparedQuery(c: Connection, sql: String, vararg args: Any): PreparedStatement {
        val query = c.prepareStatement(sql)
        args.forEachIndexed { index, s -> when(s) {
            is String -> query.setString(index + 1, s)
            is Int -> query.setInt(index + 1, s)
            is UInt -> query.setInt(index + 1, s.toInt())
            is Double -> query.setFloat(index + 1, s.toFloat())
        } }
        return query
    }

}