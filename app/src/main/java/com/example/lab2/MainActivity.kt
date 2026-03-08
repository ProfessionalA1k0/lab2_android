package com.example.lab2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(),
    InputFragment.Listener,
    ResultFragment.Listener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerInput, InputFragment())
                .commit()
        }
    }

    override fun onok(text: String, fontId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerResult, ResultFragment.newInstance(text, fontId))
            .commit()
    }

    override fun onCancel() {

        (supportFragmentManager.findFragmentById(R.id.containerInput) as? InputFragment)
            ?.clearForm()

        supportFragmentManager.findFragmentById(R.id.containerResult)?.let { result ->
            supportFragmentManager.beginTransaction()
                .remove(result)
                .commit()
        }
    }
}