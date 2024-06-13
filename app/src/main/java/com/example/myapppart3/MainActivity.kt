package com.example.myapppart3

import android.annotation.SuppressLint
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

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Исправленный код
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        //     ^^^^^^^^^^^^^^^^^^^^^^

        itemAdapter = ItemAdapter(emptyList())

        binding.itemList.layoutManager = LinearLayoutManager(this)
        binding.itemList.adapter = itemAdapter

        viewModel.itemsLiveData.observe(this) { items ->
            itemAdapter.items = items
            itemAdapter.notifyDataSetChanged()
        }

        binding.addButton.setOnClickListener {
            showInputDialog()
        }
    }

    private fun showInputDialog() {
        val inputDialog = InputDialog(this)
        inputDialog.show { inputText ->
            viewModel.addItem(inputText)
        }
    }
}
