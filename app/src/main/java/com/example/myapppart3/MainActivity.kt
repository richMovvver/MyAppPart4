package com.example.myapppart3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapppart3.databinding.ActivityMainBinding
import com.example.myapppart3.ui.InputDialog

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Инициализация ViewModel
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Настройка RecyclerView
        itemAdapter = ItemAdapter(viewModel.items) // Передаем список айтемов из ViewModel
        binding.itemList.layoutManager = LinearLayoutManager(this)
        binding.itemList.adapter = itemAdapter

        // Обработка нажатия на кнопку "Добавить"
        binding.addButton.setOnClickListener {
            showInputDialog()
        }

        // Обработка нажатий на элементы нижней навигации (TODO: реализовать)
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.calculate_fragment -> {
                    // Переключение на фрагмент "Расчитать"
                    true
                }
                R.id.settings_fragment -> {
                    // Переключение на фрагмент "Настройки"
                    true
                }
                else -> false
            }
        }

        // Наблюдение за изменениями списка айтемов в ViewModel (TODO: реализовать в ViewModel)
        viewModel.itemsLiveData.observe(this) { items ->
            itemAdapter.items = items
            itemAdapter.notifyDataSetChanged()
        }
    }

    // Отображение диалога ввода
    private fun showInputDialog() {
        val inputDialog = InputDialog(this)
        inputDialog.show { inputText ->
            viewModel.addItem(inputText) // Добавление нового айтема в ViewModel
        }
    }
}
