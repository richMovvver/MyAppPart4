package com.example.myapppart3.data.entities

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class UserRepository(private val userDao: UserDao) {

    fun getUsers(): Flow<List<User>> {
        return userDao.getUsers()
    }

    suspend fun insertUser(user: User) {
        withContext(Dispatchers.IO) {  // Выполнение в IO потоке
            userDao.insertUser(user)
        }
    }

    suspend fun updateUser(user: User) {
        withContext(Dispatchers.IO) {
            userDao.updateUser(user)
        }
    }

    suspend fun deleteUser(user: User) {
        withContext(Dispatchers.IO) {
            userDao.deleteUser(user)
        }
    }


}