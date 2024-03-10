package org.example.database.connection

import java.io.IOException
import java.util.*
import kotlin.jvm.Throws

class PropertiesUtil {
    companion object {
        private val PROPERTIES: Properties = Properties();

        init {
            loadProperties()
        }

        @Throws(IOException::class)
        private fun loadProperties() {
            val inputStream = Companion::class.java.classLoader.getResourceAsStream("application.properties")
            inputStream.use {
                PROPERTIES.load(inputStream)
            }
        }

        fun get(key: String): String = PROPERTIES.getProperty(key)
    }
}