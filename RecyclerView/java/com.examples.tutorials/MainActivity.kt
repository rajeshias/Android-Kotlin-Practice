package com.example.tutorials

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toDoList = mutableListOf(
            ToDo("Eat Lunch", false),
            ToDo("Practice Android Recycle Viewer", true),
            ToDo("Walk Bellu", true),
            ToDo("Read Book", false),
            ToDo("Go to Sleep", false)
        )
        val todoAdapter = ToDoAdapter(toDoList)
        rvTodo.adapter = todoAdapter
        rvTodo.layoutManager = LinearLayoutManager(this)

        btnAddTodo.setOnClickListener {
            val newTodo = ToDo(etTodoInput.text.toString(), false)
            toDoList.add(newTodo)
            todoAdapter.notifyItemInserted(toDoList.size - 1)
        }
    }
}
