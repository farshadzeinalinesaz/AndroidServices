package com.global_relay.aidl_serverkotlin.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.global_relay.aidl_serverkotlin.IAppSharedTask

class AppService : Service() {
    private final val appSharedImpl: AppSharedTaskImpl = AppSharedTaskImpl()

    private class AppSharedTaskImpl : IAppSharedTask.Stub() {
        override fun calculateSum(x1: Int, x2: Int): Int {
            return x1 + x2
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return appSharedImpl
    }

}