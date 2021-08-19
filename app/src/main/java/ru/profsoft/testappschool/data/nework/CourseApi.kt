package ru.profsoft.testappschool.data.nework

import retrofit2.http.GET
import ru.profsoft.testappschool.data.model.CourseRequest

interface CourseApi {

    @GET("/courses")
    suspend fun getCourses():List<CourseRequest>

}