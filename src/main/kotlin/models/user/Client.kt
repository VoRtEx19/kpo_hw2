package org.example.models.user

import org.example.models.order.Order

class Client(username: String, password: String): User(username, password) {
    var order: Order? = null
}