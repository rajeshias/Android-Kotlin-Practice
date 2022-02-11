package com.example.tutorials

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            val call1 = networkCall1()
            val call2 = networkCall2()
            Log.d(tag, "->$call1")
            Log.d(tag, "->$call2")
        }
    }
    suspend fun networkCall1(): String {
        delay(3000L)
        return "This is first response"
    }
    suspend fun networkCall2(): String {
        delay(3000L)
        return "This is first response"
    }
}
