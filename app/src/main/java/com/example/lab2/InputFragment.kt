package com.example.lab2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import java.io.FileOutputStream

class InputFragment : Fragment() {
    interface Listener {
        fun onok(text: String, fontId: Int)
    }

    private var listener: Listener? = null
    private lateinit var rgFont: RadioGroup
    private lateinit var etInput: EditText

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? Listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_input, container, false)
        rgFont = view.findViewById(R.id.rgFont)
        etInput = view.findViewById(R.id.etInput)

        val btnOk = view.findViewById<Button>(R.id.btnOk)
        val btnOpen = view.findViewById<Button>(R.id.btnOpenHistory)

        btnOk.setOnClickListener {
            val text = etInput.text.toString()
            val selectedId = rgFont.checkedRadioButtonId

            if (text.isBlank() || selectedId == -1) {
                Toast.makeText(requireContext(), "Завершіть введення всіх даних", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            saveToFile(text, selectedId)

            listener?.onok(text, selectedId)
        }

        btnOpen.setOnClickListener {
            val intent = Intent(requireContext(), HistoryActivity::class.java)
            startActivity(intent)
        }

        return view
    }

    private fun saveToFile(text: String, fontId: Int) {
        val fontName = when (fontId) {
            R.id.rbSans -> "Sans Serif"
            R.id.rbSerif -> "Serif"
            R.id.rbMono -> "Monospace"
            else -> "Default"
        }

        val dataToSave = "Текст: $text | Шрифт: $fontName\n"

        try {
            val fileOutputStream: FileOutputStream =
                requireContext().openFileOutput("history.txt", Context.MODE_APPEND)
            fileOutputStream.write(dataToSave.toByteArray())
            fileOutputStream.close()
            Toast.makeText(requireContext(), "Дані успішно збережено", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(requireContext(), "Помилка при збереженні", Toast.LENGTH_SHORT).show()
        }
    }

    fun clearForm() {
        etInput.text.clear()
        rgFont.clearCheck()
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }
}