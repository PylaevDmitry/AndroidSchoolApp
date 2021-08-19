package ru.profsoft.testappschool.data.nework

import retrofit2.http.GET
import ru.profsoft.testappschool.data.model.UserRes

interface UserApi {

    @GET("/users")
    suspend fun getUsers():List<UserRes>

}