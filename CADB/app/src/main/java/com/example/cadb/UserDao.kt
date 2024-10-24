package com.example.cadb

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun getAllUsers(): LiveData<List<User>>

    @Delete
    suspend fun deleteUser(user: User)
}
