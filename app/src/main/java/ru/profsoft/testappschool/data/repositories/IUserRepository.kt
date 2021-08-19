package ru.profsoft.testappschool.data.repositories

import ru.profsoft.testappschool.data.local.entity.Course

interface IUserRepository {

    suspend fun getCoursesFromDatabase():List<Course>

    suspend fun loadCoursesFromNetwork():List<Course>

    suspend fun insertCourses(courses:List<Course>)
}