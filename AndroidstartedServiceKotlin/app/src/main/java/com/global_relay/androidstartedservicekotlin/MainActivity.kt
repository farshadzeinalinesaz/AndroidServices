package com.global_relay.androidstartedservicekotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.global_relay.androidstartedservicekotlin.service.AppIntentService
import com.global_relay.androidstartedservicekotlin.service.AppService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStartService.setOnClickListener(this::onClick)
        btnStopService.setOnClickListener(this::onClick)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnStartService->{
                //var intent:Intent = Intent(this,AppService::class.java)
                var intent:Intent = Intent(this,AppIntentService::class.java)
                startService(intent)
            }
            R.id.btnStopService->{
                //var intent:Intent = Intent(this,AppService::class.java)
                var intent:Intent = Intent(this,AppIntentService::class.java)
                stopService(intent)
            }
            else->{

            }
        }
    }
}
