package com.example.tutorials

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    // Needs to be global to use in onOptionsItemSelected, so have to initiate it with a promise
    lateinit var toggle: ActionBarDrawerToggle

    val CHANNEL_ID = "Channel ID"
    val CHANNEL_NAME = "Channel Name"
    val NOTIFICATION_ID = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createNotificationServiceChannel()

        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(this).run{
            addNextIntentWithParentStack(intent)
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("You just fell in love with android")
            .setContentText("Please click to continue")
            .setSmallIcon(R.drawable.ic_heart)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) // This is queue priority, NOT Importance
            .setContentIntent(pendingIntent)
            .build()

        val notificationManager = NotificationManagerCompat.from(this)

        btnShowNotification.setOnClickListener {
            notificationManager.notify(NOTIFICATION_ID, notification)
        }

        toggle = ActionBarDrawerToggle(this, dl, R.string.draw_open, R.string.draw_close )
        dl.addDrawerListener(toggle)
        toggle.syncState()

        // for back button when open
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nv.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.Profile -> Toast.makeText(applicationContext, "Welcome to Profile", Toast.LENGTH_SHORT).show()
                R.id.Shop -> Toast.makeText(applicationContext, "Welcome to SideShop", Toast.LENGTH_SHORT).show()
                R.id.Settings -> Toast.makeText(applicationContext, "Welcome to Settings", Toast.LENGTH_SHORT).show()
                R.id.SignOut -> finish()
            }
            true
        }

        val sharedPref = getSharedPreferences("shared preferences", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        btnSave.setOnClickListener {
            val name = etName.text?.toString()
            var age = 0
            if(etAge.text.toString() != ""){
                age = etAge.text.toString().toInt()
            }
            val isAdult = cbAdult.isChecked

            editor.apply{
                putString("name", name)
                putInt("age", age)
                putBoolean("adult", isAdult)
                apply()
            }
        }

        btnLoad.setOnClickListener {
            etName.setText(sharedPref.getString("name", ""))
            etAge.setText(sharedPref.getInt("age", 18).toString())
            cbAdult.isChecked = sharedPref.getBoolean("adult", true)
        }
        btnStartService.setOnClickListener {
            Intent(this, MyService::class.java).also {
                startService(it)
                tvService.setText("MyService Running")
            }
        }
        btnStopService.setOnClickListener {
            Intent(this, MyService::class.java).also {
                stopService(it)
                tvService.setText("MyService Stopped")
            }
        }
        btnSendData.setOnClickListener {
            Intent(this, MyService::class.java).also {
                val data = etSendData.text.toString()
                it.putExtra("EXTRA_DATA", data)
                startService(it)
            }
        }


    }

    fun createNotificationServiceChannel() {
        // This entire notification build works only for Android Oreo or greater versions
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply{
                lightColor = Color.GREEN
                enableLights(true)
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
