package com.example.mediwithu

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import androidx.core.app.NotificationCompat

class MyNotificationHelpher(private val context: Context) {

    fun showNotification(title:String?, message:String?){
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "one-channel"
            val channelName = "FCM Channel"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
            builder = NotificationCompat.Builder(context, channelId)
        }
        else {
            builder = NotificationCompat.Builder(context)
        }

        builder.run {
            setSmallIcon(R.drawable.ic_stat_ic_notification)
            setWhen(System.currentTimeMillis())
            setContentTitle(title)
            setContentText(message)
            setAutoCancel(true)
        }

        notificationManager.notify(11, builder.build())

    }
}