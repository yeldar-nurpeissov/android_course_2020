package com.example.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.SystemClock
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.*
import java.util.concurrent.TimeUnit

class ShowNotificationWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {


    companion object {
        private const val KEY_DELAY = "key_delay"

        fun launch(context: Context, delay: Long) {
            val inputData = Data.Builder()
                .putLong(KEY_DELAY, delay)
                .build()

            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .setRequiresCharging(false)

            val repeatInterval = 15L
            val flexInterval = 10L

            val request = PeriodicWorkRequest.Builder(
                ShowNotificationWorker::class.java,
                repeatInterval,
                TimeUnit.MINUTES,
                flexInterval,
                TimeUnit.SECONDS
            )
                .setInputData(inputData)
                .setConstraints(constraints.build())
                .build()

            val uniqueWork = OneTimeWorkRequestBuilder<ShowNotificationWorker>()
                .setInputData(inputData)
                .setConstraints(constraints.build())
                .build()

            val existingWorkPolicy = ExistingWorkPolicy.APPEND_OR_REPLACE

            WorkManager
                .getInstance(context)
                .beginUniqueWork("test_worker_name", existingWorkPolicy ,uniqueWork)
                .then(uniqueWork)
                .enqueue()
        }
    }

    private val delay by lazy {
        inputData.getLong(KEY_DELAY, 0)
    }

//    private val repo:MyRepo by inject()

    override suspend fun doWork(): Result {
        shoNotification()

        //repo.fetchData()
        return Result.success()
    }

    private fun shoNotification() {
        val builder = NotificationCompat.Builder(applicationContext, "wwegwegwegw")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle("My notification from worker")
            .setContentText("Much longer text that cannot fit one line...")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Much longer text that cannot fit one line...")
            )

        createNotificationChannel()

        with(NotificationManagerCompat.from(applicationContext)) {
            notify(SystemClock.elapsedRealtime().toInt(), builder.build())
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "getString(R.string.channel_name)"
            val descriptionText = "getString(R.string.channel_description)"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("wwegwegwegw", name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}