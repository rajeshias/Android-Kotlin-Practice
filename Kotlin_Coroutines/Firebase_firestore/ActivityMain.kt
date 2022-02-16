package com.example.tutorials

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await


data class Person(
    val name: String = "",
    val age: Int = -1
)
    
    
class MainActivity : AppCompatActivity() {

    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val testDocument = Firebase.firestore.collection("test").document("ThisisfromKotlinPog")
        val rajesh = Person("Rajesh", 25)
        GlobalScope.launch(Dispatchers.IO) {
            delay(3000L)
            testDocument.set(rajesh).await()
            val person = testDocument.get().await().toObject(Person::class.java)
            withContext(Dispatchers.Main){
                tvNext.text = person.toString()
            }
        }

    }
}
