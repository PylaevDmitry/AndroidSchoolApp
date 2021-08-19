package ru.profsoft.testappschool.app.di.module


import ru.profsoft.testappschool.data.repositories.IUserRepository
import ru.profsoft.testappschool.data.repositories.UserRepository
import dagger.Module
import dagger.Provides
import ru.profsoft.testappschool.data.local.UsersDatabase
import ru.profsoft.testappschool.data.nework.UserApi


@Module
class RepositoryModule {

    @Provides
    fun provideUserRepository(
        userApi: UserApi,
        usersDatabase: UsersDatabase
    ) : IUserRepository = UserRepository(userApi, usersDatabase)

}