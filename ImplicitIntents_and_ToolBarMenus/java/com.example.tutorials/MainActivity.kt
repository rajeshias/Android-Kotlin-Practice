package com.example.tutorials

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnTakePhoto.setOnClickListener {
            Intent(Intent.ACTION_GET_CONTENT).also{
                it.type = "image/*"
                startActivityForResult(it, 0)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == 0){
            val uri = data?.data
            ivPhoto.setImageURI((uri))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.miAddEntity -> Toast.makeText(this, "You clicked on Add Entity", Toast.LENGTH_SHORT).show()
            R.id.miFavourites -> Toast.makeText(this, "You clicked on Favourites <3", Toast.LENGTH_SHORT).show()
            R.id.miSettings -> Toast.makeText(this, "You clicked on Settings UwU", Toast.LENGTH_SHORT).show()
            R.id.miGiveFeedback -> Toast.makeText(this, "You want to give some feedback", Toast.LENGTH_SHORT).show()
            R.id.miHelp -> Toast.makeText(this, "there!, there!. It will alright", Toast.LENGTH_SHORT).show()
            R.id.miExit -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
