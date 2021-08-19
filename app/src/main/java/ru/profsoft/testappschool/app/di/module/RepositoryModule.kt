package ru.profsoft.testappschool.app.di.module


import ru.profsoft.testappschool.data.repositories.IUserRepository
import ru.profsoft.testappschool.data.repositories.UserRepository
import dagger.Module
import dagger.Provides
import ru.profsoft.testappschool.data.local.CourseDatabase
import ru.profsoft.testappschool.data.nework.CourseApi

@Module
class RepositoryModule {

    @Provides
    fun provideUserRepository(
        courseApi: CourseApi,
        courseDatabase: CourseDatabase
    ) : IUserRepository = UserRepository(courseApi, courseDatabase)

}