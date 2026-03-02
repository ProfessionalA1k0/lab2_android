package com.example.lab2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment

class InputFragment : Fragment() {

    interface Listener {
        fun onOk(text: String, fontId: Int)
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

        btnOk.setOnClickListener {
            val text = etInput.text.toString()
            val selectedId = rgFont.checkedRadioButtonId

            if (text.isBlank() || selectedId == -1) {
                Toast.makeText(requireContext(), "Завершіть введення всіх даних", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            listener?.onOk(text, selectedId)
        }

        return view
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