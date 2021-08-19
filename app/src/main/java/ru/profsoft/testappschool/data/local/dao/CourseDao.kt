package ru.profsoft.testappschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.profsoft.testappschool.data.local.entity.Course

@Dao
interface CourseDao {
    @Query("SELECT * FROM Courses")
    suspend fun getCourses(): List<Course>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCourses(userEntities: List<Course>)
}