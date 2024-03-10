package org.example.application

import org.example.application.console.ConsoleInterface
import org.example.database.repository.authentication.UserRepositoryImpl
import org.example.database.repository.menu.DishRepositoryImpl
import org.example.database.repository.revenue.RevenueRepositoryImpl
import org.example.infrastructure.authentication.AuthServer
import org.example.infrastructure.restaurant.kitchen.Kitchen
import org.example.service.authentication.AuthServiceImpl
import org.example.service.menu.MenuServiceImpl
import org.example.service.revenue.RevenueServiceImpl

fun configure(): Application {
    val app = Application(ConsoleInterface())

    // user auth
    val userRepo = UserRepositoryImpl()
    app.authServer = AuthServer(AuthServiceImpl(userRepo), app.appInterface.authInterface)

    // kitchen
    val dishRepo = DishRepositoryImpl()
    val revenueRepo = RevenueRepositoryImpl()
    app.kitchen = Kitchen(MenuServiceImpl(dishRepo), RevenueServiceImpl(revenueRepo))

    return app
}