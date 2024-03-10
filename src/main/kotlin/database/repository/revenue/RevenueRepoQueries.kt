package org.example.database.repository.revenue

val createRevenueTableRequest = """
    create table if not exists Revenue (
        id serial primary key,
        revenue integer check (revenue >= 0));
    """.trimIndent()

val getAllRevenueRequest = """
    select * from Revenue;
""".trimIndent()

val getRevenueByIdRequest = """
    select * from Revenue where Revenue.id = ?;
""".trimIndent()

val insertRevenueRequest = """
    insert into Revenue (revenue)
    values (?);
""".trimIndent()

val updateRevenueRequest = """
    update Revenue
    set revenue = ?
    where id = ?;
""".trimIndent()

val deleteRevenueRequest = """
    delete from Revenue
    where id = ?;
""".trimIndent()