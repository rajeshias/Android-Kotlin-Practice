package com.example.tutorials

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val display = intent.getSerializableExtra("EXTRA_BURGER") as Burger
        tvSecond.text = display.toString()
        btnBack.setOnClickListener {
            finish()
        }
    }


}
