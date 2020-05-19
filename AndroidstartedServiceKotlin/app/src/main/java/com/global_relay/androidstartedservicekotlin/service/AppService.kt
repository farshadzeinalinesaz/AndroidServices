package com.global_relay.androidstartedservicekotlin.service

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.global_relay.androidstartedservicekotlin.R

class AppService: Service()
{
    private final var player:MediaPlayer? = null
    override fun onBind(intent: Intent?): IBinder? {
        return null;
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        player = MediaPlayer.create(this, R.raw.aa)
        player?.start()
        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        player?.apply {
            stop()
            release()
        }
        super.onDestroy()
    }

}