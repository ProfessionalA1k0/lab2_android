package com.example.lab2

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.File

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        val tvContent = findViewById<TextView>(R.id.tvHistoryContent)
        val btnBack = findViewById<Button>(R.id.btnBack)
        val btnDelete = findViewById<Button>(R.id.btnDeleteHistory)

        displayHistory(tvContent)

        btnBack.setOnClickListener {
            finish()
        }

        btnDelete.setOnClickListener {
            val file = File(filesDir, "history.txt")
            if (file.exists()) {
                file.delete()
                tvContent.text = "Сховище пусте"
                Toast.makeText(this, "Історію видалено", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun displayHistory(textView: TextView) {
        val file = File(filesDir, "history.txt")
        if (file.exists() && file.length() > 0L) {
            val content = file.readText()
            textView.text = content
        } else {
            textView.text = "Сховище пусте"
        }
    }
}