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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addContactDialog = AlertDialog.Builder(this)
            .setTitle("Add Contact")
            .setIcon(R.drawable.ic_add_contact)
            .setMessage("Do you want to do this?")
            .setPositiveButton("yes"){_,_ ->
                Toast.makeText(this, "You clicked Yes :)", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("no"){_,_ ->
                Toast.makeText(this, "You clicked No :(", Toast.LENGTH_SHORT).show()
            }.create()

        val gender = arrayOf("Male", "Female", "Other")
        val chooseGender = AlertDialog.Builder(this)
            .setTitle("Choose Your Gender")
            .setSingleChoiceItems(gender, 0) { _, i ->
                Toast.makeText(this, "You chose ${gender[i]} :)", Toast.LENGTH_SHORT).show()
            }.setPositiveButton("Accept"){_,_ ->
                Toast.makeText(this, "Well, Thank you for accepting :D", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Not Today Buddy"){_,_ ->
                Toast.makeText(this, "Sometime again soon then T_T", Toast.LENGTH_SHORT).show()
            }.create()

        val capacity = arrayOf("1 litre", "15 litre", "20 litre", "25 litre")
        val chooseCapacity = AlertDialog.Builder(this)
            .setTitle("Choose Your Gender")
            .setMultiChoiceItems(capacity, booleanArrayOf(false, false, false, false)) { _, i, isChecked ->
                if(isChecked){
                    Toast.makeText(this, "You checked ${capacity[i]} :)", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "You unchecked ${capacity[i]} :(", Toast.LENGTH_SHORT).show()
                }
            }.setPositiveButton("Accept"){_,_ ->
                Toast.makeText(this, "Well, Thank you for accepting :D", Toast.LENGTH_SHORT).show()

            }
            .setNegativeButton("Not Today Buddy"){_,_ ->
                Toast.makeText(this, "Sometime again soon then T_T", Toast.LENGTH_SHORT).show()
            }.create()


        btnDialog1.setOnClickListener {
            addContactDialog.show()
        }
        btnDialog2.setOnClickListener {
            chooseGender.show()
        }
        btnDialog3.setOnClickListener {
            chooseCapacity.show()
        }

        spYear.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(this@MainActivity, "You just selected ${p0?.getItemAtPosition(p2).toString()}", Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        val customList = mutableListOf<String>()
        btnAddToSpinner.setOnClickListener {
            customList.add(etAddToSpinner.text.toString())
            val adapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, customList)
            spCustom.adapter = adapter
        }
        spCustom.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(this@MainActivity, "Hmmm, Looks like you are a ${p0?.getItemAtPosition(p2).toString()}", Toast.LENGTH_SHORT).show()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }
}
