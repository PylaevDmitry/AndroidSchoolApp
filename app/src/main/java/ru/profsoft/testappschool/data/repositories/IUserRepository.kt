package ru.profsoft.testappschool.data.repositories

import ru.profsoft.testappschool.data.local.entity.User


interface IUserRepository {

    suspend fun getUsersFromDatabase():List<User>

    suspend fun loadUsersFromNetwork():List<User>

    suspend fun insertUsers(users:List<User>)
}