package org.example.database.repository.authentication

val createUserTableRequest = """
    create table if not exists Users (
        username varchar(256) primary key,
        password varchar(256),
        role varchar(6) check (role in ('client', 'admin')),
        active varchar(5) check (active in ('TRUE', 'FALSE')));
    """.trimIndent()

val getAllUsersRequest = """
    select * from Users;
""".trimIndent()

val getUserByUsernameRequest = """
    select * from Users where Users.username = ?;
""".trimIndent()

val insertUserRequest = """
    insert into Users (username, password, role, active)
    values (?, ?, ?, ?);
""".trimIndent()

val updateUserRequest = """
    update Users
    set password = ?,
        role = ?,
        active = ?
    where username = ?;
""".trimIndent()

val deleteUserRequest = """
    delete from Users
    where username = ?;
""".trimIndent()