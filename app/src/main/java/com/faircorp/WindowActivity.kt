package com.faircorp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.faircorp.service.WindowService


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
        val window = windowService.findById(id)
        if (window != null) {
            findViewById<TextView>(R.id.txt_window_name_wa).text = window.name
            findViewById<TextView>(R.id.txt_room_name_wa).text = window.room.name
            findViewById<TextView>(R.id.txt_window_current_temperature_wa).text = window.room.currentTemperature?.toString()
            findViewById<TextView>(R.id.txt_window_target_temperature_wa).text = window.room.targetTemperature?.toString()
            findViewById<TextView>(R.id.txt_window_status_wa).text = window.status.toString()
        }
    }
}