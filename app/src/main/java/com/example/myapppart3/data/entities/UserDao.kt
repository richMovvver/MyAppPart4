package com.example.myapppart3.data.entities

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) // Изменено
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM User")
    fun getUsers(): Flow<List<User>>

    @Update
    suspend fun updateUser(user: User)

    @Delete // Добавляем аннотацию @Delete
    suspend fun deleteUser(user: User)
}