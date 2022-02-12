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

        runBlocking {
            launch(Dispatchers.IO) {
                delay(3000L)
                Log.d(tag, "Finished IO Coroutine 1")
            }
            launch(Dispatchers.IO) {
                delay(3000L)
                Log.d(tag, "Finished IO Coroutine 2")
            }
            Log.d(tag, "Start of runBlocking")
            delay(2000L)
            Log.d(tag, "End of runBlocking")
        }
        Log.d(tag, "After runBlocking in mainThread")
    }
}
