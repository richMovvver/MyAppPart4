package com.example.myapppart3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapppart3.data.entities.User
import com.example.myapppart3.databinding.ItemListItemBinding

class ItemAdapter(
    var items: List<User>,
    private val onUserCheckedChangeListener: (User, Boolean) -> Unit,
    private val onEditClickListener: (User) -> Unit,
    private val onDeleteClickListener: (User) -> Unit
    ) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(val binding: ItemListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        lateinit var user: User
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            ItemListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.user = items[position]
        holder.binding.itemText.text = holder.user.name
        holder.binding.checkbox.isChecked = holder.user.isChecked
        holder.binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
            onUserCheckedChangeListener(holder.user, isChecked)
        }
        holder.binding.editButton.setOnClickListener {
            onEditClickListener(holder.user)
        }

        holder.binding.deleteButton.setOnClickListener {
            onDeleteClickListener(holder.user)
        }
    }

    override fun getItemCount(): Int = items.size
}