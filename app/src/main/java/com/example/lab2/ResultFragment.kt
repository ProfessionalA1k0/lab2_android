package com.example.lab2

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class ResultFragment : Fragment() {

    interface Listener {
        fun onCancel()
    }

    private var listener: Listener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listener = activity as? Listener
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_result, container, false)

        val tvResult = view.findViewById<TextView>(R.id.tvResult)
        val btnCancel = view.findViewById<Button>(R.id.btnCancel)

        val text = requireArguments().getString(ARG_TEXT).orEmpty()
        val fontId = requireArguments().getInt(ARG_FONT_ID)

        val typeface = when (fontId) {
            R.id.rbSans -> Typeface.SANS_SERIF
            R.id.rbSerif -> Typeface.SERIF
            R.id.rbMono -> Typeface.MONOSPACE
            else -> Typeface.DEFAULT
        }

        tvResult.text = text
        tvResult.typeface = typeface

        btnCancel.setOnClickListener {
            listener?.onCancel()
        }

        return view
    }

    companion object {
        private const val ARG_TEXT = "text"
        private const val ARG_FONT_ID = "fontId"

        fun newInstance(text: String, fontId: Int): ResultFragment {
            return ResultFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_TEXT, text)
                    putInt(ARG_FONT_ID, fontId)
                }
            }
        }
    }
}