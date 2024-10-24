package com.example.hostelf

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user_table WHERE mobile = :mobile AND password = :password LIMIT 1")
    suspend fun login(mobile: String, password: String): User?

    @Query("SELECT COUNT(*) FROM user_table")
    fun getBookedRoomsCount(): LiveData<Int>
}
