package com.example.myapppart3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapppart3.data.entities.User
import com.example.myapppart3.data.entities.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    // LiveData для списка пользователей из базы данных
    private val _users = MutableLiveData<List<User>>(emptyList())
    val users: LiveData<List<User>> = _users

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
    fun addUser(name: String) {
        viewModelScope.launch {
            val user = User(name = name, age = 0) // Возраст не используется, поэтому ставим 0
            userRepository.insertUser(user)
        }
    }

    fun updateUserCheckedStatus(user: User, isChecked: Boolean) {
        viewModelScope.launch {
            user.isChecked = isChecked
            userRepository.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            userRepository.deleteUser(user)
        }
    }

    fun updateUser(user: User, newName: String) {
        viewModelScope.launch {
            user.name = newName // Изменение имени пользователя
            userRepository.updateUser(user)
        }
    }
}