package com.example.myapppart3.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapppart3.ItemAdapter
import com.example.myapppart3.databinding.FragmentMainBinding
import com.example.myapppart3.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModels(ownerProducer = { this })
    private lateinit var itemAdapter: ItemAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        itemAdapter = ItemAdapter(emptyList()) { user, isChecked ->
            viewModel.updateUserCheckedStatus(user, isChecked)
        }
        binding.itemList.layoutManager = LinearLayoutManager(requireContext())
        binding.itemList.adapter = itemAdapter

        viewModel.users.observe(viewLifecycleOwner) { users ->
            itemAdapter.items = users // Теперь передаем список User
            itemAdapter.notifyDataSetChanged()
        }

        binding.addButton.setOnClickListener {
            showInputDialog()
        }
    }

    private fun showInputDialog() {
        val inputDialog = InputDialog(requireContext())
        inputDialog.show { inputText ->
            viewModel.addUser(inputText) // Используем addUser()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}