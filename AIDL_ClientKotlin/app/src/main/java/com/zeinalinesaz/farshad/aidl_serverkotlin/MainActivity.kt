package com.zeinalinesaz.farshad.aidl_serverkotlin

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private var iAppSharedTask: IAppSharedTask? = null

    private val serviceConnection: ServiceConnection = object: ServiceConnection{
        override fun onServiceDisconnected(name: ComponentName?) {
            println("Binder is disconnected")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            iAppSharedTask = IAppSharedTask.Stub.asInterface(service)
            println("Binder is connected")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCalculator.setOnClickListener(this::onClick)

        val implicitIntent:Intent = Intent("here.is.my.action.name")
        //In android 6.0 and above we cannot have implicit action
        val intent: Intent? = convertImplicitIntentToExplicitIntent(implicitIntent,this)
        bindService(intent,serviceConnection, Context.BIND_AUTO_CREATE)
    }

    override fun onClick(v: View?) {
        try{
            val x1: Int = editNum1.text.toString().toInt()
            val x2: Int = editNum2.text.toString().toInt()
            //Do some user input validation

            iAppSharedTask?.let {
                val result: Int = it.calculateSum(x1,x2)
                Toast.makeText(this@MainActivity,"The Calculate Sum is $result",Toast.LENGTH_LONG).show()
            }
        }
        catch (ex:Exception){
            print(ex.message)
        }

    }

    fun convertImplicitIntentToExplicitIntent(implicitIntent: Intent?, context: Context): Intent? {
        val pm : PackageManager = context.packageManager
        val resolveInfoList : MutableList<ResolveInfo?> = pm.queryIntentServices(implicitIntent, 0)
        if (resolveInfoList.size != 1) {
            return null
        }
        val serviceInfo : ResolveInfo? = resolveInfoList[0]
        val component = ComponentName(serviceInfo?.serviceInfo!!.packageName, serviceInfo.serviceInfo.name)
        val explicitIntent = Intent(implicitIntent)
        explicitIntent.component = component
        return explicitIntent
    }
}
