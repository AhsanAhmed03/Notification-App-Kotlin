package com.example.notificationappkotlin

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "my_channel"
    private val NOTIFICATION_ID = 1

    private var notificationManager: NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        createNotification_channel()

    }

    fun showNotification(view: View) {
        val intent = Intent(this,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,0,
            intent,PendingIntent.FLAG_MUTABLE)
        var builder : Notification.Builder? =null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            builder = Notification.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.notifiction_icon)
                .setContentTitle("LIKE & SUBSCRIBE")
                .setContentText("MOBILE APP DEVELOPMENT")
                .setPriority(Notification.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        }
        val notification : Notification
        notification = builder!!.build()
        notificationManager!!.notify(NOTIFICATION_ID,notification)
    }

    private fun createNotification_channel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            val channelName : CharSequence = "MY_Channel"
            val channelDescription = "Description_Channel"
            val importance =NotificationManager.IMPORTANCE_DEFAULT
            val  channel = NotificationChannel(CHANNEL_ID,channelName,importance)
            channel.description = channelDescription
            notificationManager!!.createNotificationChannel(channel)
        }
    }
}