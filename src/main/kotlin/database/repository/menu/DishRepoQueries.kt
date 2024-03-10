package org.example.database.repository.menu

val createDishTableRequest = """
    create table if not exists Dish (
        name varchar(256) primary key,
        ingredients varchar(512),
        portion_count integer check (portion_count >= 0),
        price float check (price >= 0),
        cooking_time integer check (cooking_time >= 0),
        is_on_menu varchar(5) check (is_on_menu in ('TRUE', 'FALSE')));
    """.trimIndent()

val getAllDishesRequest = """
    select * from Dish;
""".trimIndent()

val getDishByNameRequest = """
    select * from Dish where Dish.name = ?;
""".trimIndent()

val insertDishRequest = """
    insert into Dish (name, ingredients, portion_count, price, cooking_time, is_on_menu)
    values (?, ?, ?, ?, ?, ?);
""".trimIndent()

val updateDishRequest = """
    update Dish
    set ingredients = ?,
        portion_count = ?,
        price = ?,
        cooking_time = ?,
        is_on_menu = ?
    where name = ?;
""".trimIndent()

val deleteDishRequest = """
    delete from Dish
    where name = ?;
""".trimIndent()