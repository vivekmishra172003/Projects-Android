package com.example.radhika

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user_table")
    suspend fun getAllUsers(): List<User>
}
