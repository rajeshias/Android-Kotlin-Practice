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
        val job = GlobalScope.launch {
            Log.d(tag, "Starting Long running Calculation")
            withTimeout(3000L){
                for (i in 30..40){
                    if(isActive){
                        Log.d(tag, "Result is: $i: ${fib(i)}")
                    }
                }
            }
            Log.d(tag, "Ending the fibonacci calculation...")
        }
    }

    private fun fib(n: Int): Long{
        return if(n == 0) 0
        else if(n == 1) 1
        else fib(n-1) + fib(n-2)
    }
}
