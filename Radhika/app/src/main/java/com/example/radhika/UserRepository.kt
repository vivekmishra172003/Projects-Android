package com.example.radhika

class UserRepository(private val userDao: UserDao) {
    suspend fun insertUser(user: User) = userDao.insertUser(user)
    suspend fun getAllUsers(): List<User> = userDao.getAllUsers()
}
