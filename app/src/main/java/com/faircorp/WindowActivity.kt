package com.faircorp

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.faircorp.service.ApiServices
import com.faircorp.model.WindowDto
import com.faircorp.service.WindowService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


const val WINDOW_NAME_PARAM2 = "com.faircorp.windowname.attribute"
val windowService = WindowService()
class WindowActivity : BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        //creates the action bar in the activity
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val param = intent.getStringExtra(WINDOW_NAME_PARAM2)
        val windowName = findViewById<TextView>(R.id.txt_window_nom)
        windowName.text = param

        //updating fields when user clicked window row in windows list (WindowsActivityAdapter triggers this event)
        val id = intent.getLongExtra(WINDOW_NAME_PARAM, 0)
        var window=WindowDto()
        GlobalScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().windowsApiService.findById(id).execute() }
                    .onSuccess {
                        withContext(context = Dispatchers.Main) {
                           window= it.body()!!
                            if (window != null) {
                                findViewById<TextView>(R.id.txt_window_name_wa).text = window.name
                                findViewById<TextView>(R.id.txt_window_nom).text = "WINDOW_NAME : "+window.name
                                findViewById<TextView>(R.id.txt_room_name_wa).text ="ROOM_NAME : "+ window.room?.name
                                findViewById<TextView>(R.id.txt_window_current_temperature_wa).text ="CURNT_TEMP : "+ window.room?.currentTemperature?.toString()+": ℃"
                                findViewById<TextView>(R.id.txt_window_target_temperature_wa).text = "TARGET_TEMP : "+window.room?.targetTemperature?.toString()+": ℃"
                                findViewById<TextView>(R.id.txt_window_status_wa).text ="STATUS : "+ window.windowStatus.toString()
                            }else{
                                Toast.makeText(
                                        applicationContext,
                                        "WINDOW DATA NOT FOUND $it",
                                        Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }
                    .onFailure {
                        withContext(context = Dispatchers.Main) { // (3)
                            Toast.makeText(
                                    applicationContext,
                                    "Error on windows loading $it",
                                    Toast.LENGTH_LONG
                            ).show()
                        }
                    }
        }
    }
}