package com.zeinalinesaz.farshad.androidstartedservicekotlin.service

import android.app.IntentService
import android.content.Intent
import android.media.MediaPlayer
import com.zeinalinesaz.farshad.androidstartedservicekotlin.R

class AppIntentService: IntentService("AppIntentService")
{
    var player:MediaPlayer? = null
    override fun onHandleIntent(intent: Intent?) {
        player = MediaPlayer.create(this, R.raw.voice_file)
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