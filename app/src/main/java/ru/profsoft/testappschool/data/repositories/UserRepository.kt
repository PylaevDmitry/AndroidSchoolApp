package ru.profsoft.testappschool.data.repositories

import ru.profsoft.testappschool.data.local.CourseDatabase
import ru.profsoft.testappschool.data.local.entity.Course
import ru.profsoft.testappschool.data.nework.CourseApi
import ru.profsoft.testappschool.extentions.toCourse

class UserRepository(
    private val userApi: CourseApi,
    private val usersDatabase: CourseDatabase
) : IUserRepository {

    private val usersDao = usersDatabase.courseDao()

    override suspend fun getCoursesFromDatabase() = usersDao.getCourses()

    override suspend fun loadCoursesFromNetwork() :List<Course> {
        return userApi.getCourses().map { courseRequest -> courseRequest.toCourse() }
    }

    override suspend fun insertCourses(courses: List<Course>) {
        usersDao.saveCourses(courses)
    }
}