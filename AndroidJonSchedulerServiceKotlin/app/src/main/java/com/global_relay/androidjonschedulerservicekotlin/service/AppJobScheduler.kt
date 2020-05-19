package com.global_relay.androidjonschedulerservicekotlin.service

import android.app.job.JobParameters
import android.app.job.JobService
import androidx.core.util.rangeTo

//It runs in Main Thread
class AppJobScheduler : JobService() {
    private final var isCancelled: Boolean = false
    override fun onStartJob(params: JobParameters?): Boolean {
        println("onStartJob has invoked")
        doInBackground(params)
        //return false //if method execution done
        return true;
    }

    private fun doInBackground(params: JobParameters?): Unit {
        val thread: Thread = object : Thread() {
            override fun run() {
                super.run()
                for (counter in 0..10 step 1) {
                    if (isCancelled) {
                        return
                    }
                    println("The Job's Counter = $counter")
                    try {
                        Thread.sleep(1000)
                    } catch (ex: InterruptedException) {
                    }
                }
                jobFinished(params, false)
            }
        }
        thread.start()
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        println("onStopJob has invoked")
        isCancelled = true
        //return true //if after job canceled you want OS reschedule it again.
        return false //if after job canceled you do not want OS reschedule it again.
    }
}