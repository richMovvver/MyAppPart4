package com.example.myapppart3.data.entities

import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    fun getUsers(): Flow<List<User>> {
        return userDao.getUsers()
    }

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }


}