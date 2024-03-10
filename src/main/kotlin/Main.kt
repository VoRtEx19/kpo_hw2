package org.example

import kotlinx.coroutines.runBlocking
import org.example.application.configure

suspend fun main() {
    try {
        val app = configure()
        app.launch()
    } catch (e: Exception) {
        println(e.message)
    }
}