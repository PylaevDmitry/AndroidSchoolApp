package ru.profsoft.testappschool.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.profsoft.testappschool.data.local.entity.User


@Dao
interface UserDao {
    @Query("SELECT * FROM Users")
    suspend fun getUsers(): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUsers(userEntities: List<User>)
}