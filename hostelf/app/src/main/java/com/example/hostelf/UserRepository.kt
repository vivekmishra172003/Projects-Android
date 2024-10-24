package com.example.hostelf

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun login(mobile: String, password: String): User? {
        return userDao.login(mobile, password)
    }

    fun getBookedRoomsCount(): LiveData<Int> {
        return userDao.getBookedRoomsCount()
    }
}
