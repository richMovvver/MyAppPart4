package com.example.myapppart3.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var name: String,
    val age: Int,
    var isChecked: Boolean = false // Добавили поле для чекбокса
)