package ru.profsoft.testappschool.data.nework

import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApi {

    @POST("/login")
    fun login(@Query("login") name: String, @Query("password") password:Int)

}