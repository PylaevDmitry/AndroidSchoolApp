package ru.profsoft.testappschool.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.profsoft.testappschool.BuildConfig
import ru.profsoft.testappschool.data.local.dao.UserDao
import ru.profsoft.testappschool.data.local.entity.User


@Database(entities = [User::class], version = UsersDatabase.DATABASE_VERSION, exportSchema = false)
abstract class UsersDatabase() : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = BuildConfig.APPLICATION_ID + ".db"
        const val DATABASE_VERSION = 1
    }

    abstract fun userDao(): UserDao
}