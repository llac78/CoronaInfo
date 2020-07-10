package projeto.coronainfo

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit


class MainActivity() : AppCompatActivity() {

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        btnCovid?.setOnClickListener {
            startActivity(Intent(this, CovidActivity::class.java))
        }

        btnSymptoms?.setOnClickListener {
            startActivity(Intent(this, SymptomsActivity::class.java))
        }

        btnPrevention?.setOnClickListener {
            startActivity(Intent(this, PreventionActivity::class.java))
        }

        btnNumbers?.setOnClickListener {
            startActivity(Intent(this, NumbersActivity::class.java))
        }

        periodicWorkRequest()
        

    }

    override fun onStop() {
        super.onStop()
        periodicWorkRequest()
    }

    fun periodicWorkRequest() {

        val constraints: Constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
            .build()

        val periodicWorkRequest =
            PeriodicWorkRequest.Builder(NotificationWork::class.java, 8, TimeUnit.HOURS)
                .setConstraints(constraints)
                .setBackoffCriteria(BackoffPolicy.LINEAR, PeriodicWorkRequest.MIN_BACKOFF_MILLIS, TimeUnit.MILLISECONDS)
                .build()

        //Enqueuing the work request
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            "periodicWorkRequest",
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWorkRequest
        )
    }


}
