package com.example.myapppart3.ui

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.example.myapppart3.R
import com.google.android.material.textfield.TextInputEditText

class InputDialog(context: Context) {

    private val dialogView = LayoutInflater.from(context).inflate(R.layout.input_dialog, null)
    private val inputEditText = dialogView.findViewById<TextInputEditText>(R.id.input_edit_text)
    private val dialogBuilder = AlertDialog.Builder(context)
        .setView(dialogView)
        .setTitle(R.string.add_item)

    fun show(onSave: (String) -> Unit) {
        dialogBuilder
            .setPositiveButton(R.string.save) { _, _ ->
                val inputText = inputEditText.text.toString()
                onSave(inputText)
            }
            .setNegativeButton(R.string.cancel, null)
            .show()
    }
}