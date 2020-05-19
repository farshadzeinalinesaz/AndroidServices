package com.global_relay.androidstartedservicekotlin.service

import android.app.IntentService
import android.content.Intent
import android.media.MediaPlayer
import com.global_relay.androidstartedservicekotlin.R

class AppIntentService: IntentService("AppIntentService")
{
    var player:MediaPlayer? = null
    override fun onHandleIntent(intent: Intent?) {
        player = MediaPlayer.create(this,R.raw.aa)
        player?.start()
    }

    override fun onDestroy() {
        /*player?.apply {
            stop()
            release()
        }*/
        super.onDestroy()
    }
}