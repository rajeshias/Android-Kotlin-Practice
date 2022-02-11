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
            delay(1000L)
            Log.d(tag, "Coroutine says hello from thread ${Thread.currentThread().name}")
        }
        Log.d(tag, "Hello from main thread ${Thread.currentThread().name}")
    }
}


// inside gradle.app
// implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.1'
// implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.1'
