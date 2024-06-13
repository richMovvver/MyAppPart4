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

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        // Создаем адаптер для RecyclerView
        itemAdapter = ItemAdapter(emptyList()) // Создаем адаптер с пустым списком

        // Настраиваем RecyclerView
        binding.itemList.layoutManager = LinearLayoutManager(this)
        binding.itemList.adapter = itemAdapter

        // Наблюдаем за itemsLiveData в ViewModel
        viewModel.itemsLiveData.observe(this) { items ->
            // Обновляем список айтемов в адаптере
            itemAdapter.items = items
            itemAdapter.notifyDataSetChanged()
        }

        // Обработка клика на Button для добавления нового айтема
        binding.addButton.setOnClickListener {
            showInputDialog()
        }
    }

    // Показ диалога для ввода нового айтема
    private fun showInputDialog() {
        val inputDialog = InputDialog(this)
        inputDialog.show { inputText ->
            viewModel.addItem(inputText) // Добавление нового айтема в ViewModel
        }
    }
}
