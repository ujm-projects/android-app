package com.faircorp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BuildingWindowListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_building_window_list)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}