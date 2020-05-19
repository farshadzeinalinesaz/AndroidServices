package com.global_relay.androidjonschedulerservicekotlin

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.global_relay.androidjonschedulerservicekotlin.service.AppJobScheduler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private final val JOB_ID: Int = 123456789 //optional
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStartJob.setOnClickListener(this::onClick)
        btnStopJob.setOnClickListener(this::onClick)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnStartJob -> {
                val componentName: ComponentName =
                    ComponentName(this@MainActivity, AppJobScheduler::class.java)
                val jobInfo: JobInfo = JobInfo.Builder(JOB_ID, componentName)
                    .setRequiresCharging(false)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)//Wifi
                    .setPersisted(false)
                    .setPeriodic(15 * 60 * 1000)
                    .build()
                val jobScheduler: JobScheduler =
                    getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
                val jobScheduleStatus = jobScheduler.schedule(jobInfo)
                if (jobScheduleStatus == JobScheduler.RESULT_SUCCESS) {
                    println("Job scheduled successfully")
                } else {
                    println("Job schedule failed")
                }
            }
            R.id.btnStopJob -> {
                println("Stop Job scheduled is in process .....")
                val jobScheduler: JobScheduler =
                    getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
                jobScheduler.cancel(JOB_ID)
            }
            else -> {

            }
        }
    }
}
