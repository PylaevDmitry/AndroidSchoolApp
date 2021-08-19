package ru.profsoft.testappschool.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.profsoft.testappschool.BuildConfig
import ru.profsoft.testappschool.data.local.dao.CourseDao
import ru.profsoft.testappschool.data.local.entity.Course

@Database(entities = [Course::class], version = CourseDatabase.DATABASE_VERSION, exportSchema = false)
abstract class CourseDatabase() : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = BuildConfig.APPLICATION_ID + ".db"
        const val DATABASE_VERSION = 1
    }

    abstract fun courseDao(): CourseDao
}