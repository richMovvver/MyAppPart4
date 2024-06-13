package com.example.myapppart3.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapppart3.data.entities.User
import com.example.myapppart3.data.entities.UserDao

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}