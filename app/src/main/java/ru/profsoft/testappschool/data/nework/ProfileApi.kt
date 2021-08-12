package ru.profsoft.testappschool.data.nework

import retrofit2.http.GET
import retrofit2.http.Query

interface ProfileApi {

    @GET("/profile")
    fun profile(@Query("profile") content: String)

}