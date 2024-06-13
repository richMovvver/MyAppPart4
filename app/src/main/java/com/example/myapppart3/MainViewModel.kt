package com.example.myapppart3

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapppart3.data.entities.User
import com.example.myapppart3.data.entities.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
    application: Application
) : AndroidViewModel(application) {

    // LiveData для списка пользователей из базы данных
    private val _users = MutableLiveData<List<User>>(emptyList())
    val users: LiveData<List<User>> = _users

    // LiveData для списка айтемов, которые мы будем отображать
    private val _itemsLiveData = MutableLiveData<List<String>>(emptyList()) // Добавлен itemsLiveData
    val itemsLiveData: LiveData<List<String>> = _itemsLiveData

    init {
        loadUsers()
    }

    // Загрузка пользователей из базы данных
    private fun loadUsers() {
        viewModelScope.launch {
            userRepository.getUsers().collect { users ->
                _users.postValue(users)
            }
        }
    }

    // Добавление нового пользователя в базу данных
    fun addUser(name: String, age: Int) {
        viewModelScope.launch {
            val user = User(name = name, age = age)
            userRepository.insertUser(user)
        }
    }

    // Добавление нового айтема в список
    fun addItem(item: String) { // Добавлен метод addItem
        viewModelScope.launch {
            val updatedItems = _itemsLiveData.value?.toMutableList()?.apply {
                add(item)
            } ?: mutableListOf(item)
            _itemsLiveData.postValue(updatedItems)
        }
    }
}