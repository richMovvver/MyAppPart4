package com.example.myapppart3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _itemsLiveData = MutableLiveData<List<String>>(emptyList())
    val itemsLiveData: LiveData<List<String>> = _itemsLiveData

    private val _items = mutableListOf<String>()
    val items: List<String> = _items

    fun addItem(item: String) {
        _items.add(item)
        _itemsLiveData.value = _items.toList() // Обновляем LiveData
    }
}