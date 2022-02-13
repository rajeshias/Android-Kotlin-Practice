package com.example.tutorials

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis


class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        GlobalScope.launch(Dispatchers.IO) {
            val time = measureTimeMillis {
                val firstName = async { networkCall1() }
                val lastName = async { networkCall2() }
                Log.d(tag, "Your name is ${firstName.await()} ${lastName.await()}")
            }
            Log.d(tag, "networkcalls took $time ms")
        }
    }

    private suspend fun networkCall1(): String {
        delay(3000L)
        return "Rajesh"
    }
    private suspend fun networkCall2(): String {
        delay(3000L)
        return "Kanna"
    }

}
