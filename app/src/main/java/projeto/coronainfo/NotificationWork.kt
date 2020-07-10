package projeto.coronainfo

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.work.ListenableWorker.Result.retry
import androidx.work.ListenableWorker.Result.success
import androidx.work.Worker
import androidx.work.WorkerParameters


class NotificationWork(context: Context, params: WorkerParameters) : Worker(context, params) {

    val CHANNEL_ID = "default"
    val NOTIFICATION_ID = 1

    override fun doWork(): Result {

        return try {
            if (applicationContext != null){
                sendNotification(
                    applicationContext.getString(R.string.notification_title),
                    applicationContext.getString(R.string.notification_message)
                )
                success()
            } else {
                retry()
            }

        } catch (e: Exception){
            e.printStackTrace()
            Result.failure()
        }

    }

    private fun sendNotification(title: String, text: String) {
        val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                applicationContext.getString(R.string.notification_channel_name),
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = applicationContext.getString(R.string.notification_channel_description)
                enableLights(true)
                lightColor = Color.MAGENTA
                enableVibration(true)
            }
            notificationManager.createNotificationChannel(channel)
        }

        val bigTextStyle = NotificationCompat
            .BigTextStyle()
            .bigText(applicationContext.getString(R.string.notification_message))

        val notification = NotificationCompat.Builder(
            applicationContext,
            CHANNEL_ID
            )
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(R.drawable.ic_notif_message_24dp)
                .setColor(ActivityCompat.getColor(applicationContext, R.color.colorPrimary))
                .setDefaults(Notification.DEFAULT_ALL)
                .setStyle(bigTextStyle)
        notification.priority = NotificationCompat.PRIORITY_DEFAULT

        notificationManager.notify(NOTIFICATION_ID, notification.build())
    }

}