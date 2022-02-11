package com.example.tutorials

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch(Dispatchers.IO) {
            Log.d(tag, "Starting Coroutine in thread ${Thread.currentThread().name}")
            val answer = networkCall()
            withContext(Dispatchers.Main){
                Log.d(tag, "Setting value in UI using thread ${Thread.currentThread().name}")
                tvMain.text = answer
            }
        }
    }
    suspend fun networkCall(): String {
        delay(3000L)
        return "This is first response"
    }
}
