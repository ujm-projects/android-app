package com.faircorp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


const val WINDOW_NAME_PARAM2 = "com.faircorp.windowname.attribute"
class WindowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window)
        //creates the action bar in the activity
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val param = intent.getStringExtra(WINDOW_NAME_PARAM2)
        val windowName = findViewById<TextView>(R.id.txt_window_nom)
        windowName.text = param
    }
}