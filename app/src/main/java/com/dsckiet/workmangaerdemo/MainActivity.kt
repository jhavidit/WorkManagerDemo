package com.dsckiet.workmangaerdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.OneTimeWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request = PeriodicWorkRequestBuilder<WorkerClass>(1,TimeUnit.MINUTES)
            .addTag("Notification")
            .build()

            WorkManager.getInstance(this).enqueueUniquePeriodicWork("Notification",ExistingPeriodicWorkPolicy.KEEP, request)

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(request.id)
            .observe(this, Observer {
                notify_tv.append(it.state.name+"\n")
            })
    }
}