package ru.profsoft.testappschool.app.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.profsoft.testappschool.BuildConfig
import ru.profsoft.testappschool.data.local.CourseDatabase
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideApplicationContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideCourseDatabase(context: Context) : CourseDatabase = Room
        .databaseBuilder(context, CourseDatabase::class.java, CourseDatabase.DATABASE_NAME)
        .run { if (BuildConfig.DEBUG) fallbackToDestructiveMigration() else this }
        .build()
}