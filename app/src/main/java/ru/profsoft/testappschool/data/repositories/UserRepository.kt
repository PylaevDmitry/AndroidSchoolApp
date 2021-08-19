package ru.profsoft.testappschool.data.repositories

import ru.profsoft.testappschool.data.local.UsersDatabase
import ru.profsoft.testappschool.data.local.entity.User
import ru.profsoft.testappschool.extentions.toUser
import ru.profsoft.testappschool.data.nework.UserApi


class UserRepository(
    private val userApi: UserApi,
    private val usersDatabase: UsersDatabase
) : IUserRepository {

    private val usersDao = usersDatabase.userDao()

    override suspend fun getUsersFromDatabase() = usersDao.getUsers()

    override suspend fun loadUsersFromNetwork() :List<User> {
        return userApi.getUsers().map { userRes -> userRes.toUser() }
    }

    override suspend fun insertUsers(users: List<User>) {
        usersDao.saveUsers(users)
    }
}