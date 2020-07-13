package com.dsckiet.workmangaerdemo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.work.Worker
import androidx.work.WorkerParameters

class WorkerClass(val context: Context, val workerParameters: WorkerParameters) : Worker(context,workerParameters) {
    private lateinit var notificationManager: NotificationManager
    private lateinit var notificationChannel: NotificationChannel
    private lateinit var builder: Notification.Builder
    private lateinit var builder2: Notification.Builder

    private var channelID = "id"
    private var description = "Test Notification"

    override fun doWork(): Result {
       displayNotification()
        return Result.success()
    }

    private fun displayNotification() {
        notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent,
            PendingIntent.FLAG_ONE_SHOT
        )


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationChannel =
                NotificationChannel(channelID, description, NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.GREEN
            notificationChannel.enableVibration(false)
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(applicationContext, channelID)
                .setContentTitle("Notification Demo")
                .setContentText("Test Notification")
                .setSmallIcon(R.drawable.ic_launcher_round)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        applicationContext.resources,
                        R.drawable.ic_launcher
                    )
                )
                .setContentIntent(pendingIntent)



            builder2 = Notification.Builder(applicationContext, channelID)

                .setContentTitle("Notification Demo 2")
                .setContentText("Test Notification 2")
                .setSmallIcon(R.drawable.ic_launcher_round)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        applicationContext.resources,
                        R.drawable.ic_launcher
                    )
                )
                .setContentIntent(pendingIntent)


        } else {
            builder = Notification.Builder(applicationContext)

                .setContentTitle("Notification Demo")
                .setContentText("Test Notification")
                .setSmallIcon(R.drawable.ic_launcher_round)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        applicationContext.resources,
                        R.drawable.ic_launcher
                    )
                )
                .setContentIntent(pendingIntent)

            builder2 = Notification.Builder(applicationContext)

                .setContentTitle("Notification Demo 2")
                .setContentText("Test Notification 2")
                .setSmallIcon(R.drawable.ic_launcher_round)
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        applicationContext.resources,
                        R.drawable.ic_launcher
                    )
                )
                .setContentIntent(pendingIntent)

        }
        notificationManager.notify(1234, builder.build())
        notificationManager.notify(1233, builder2.build())

    }
}