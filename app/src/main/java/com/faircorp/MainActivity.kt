package com.faircorp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
//Defining Application name to make unique intent extras
const val WINDOW_NAME_PARAM = "com.faircorp.windowname.attribute"

class MainActivity : BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /** Called when the user taps the button */
//    fun openWindow(view: View) {
//        // Extract value filled in editext identified with txt_window_name id
//        val windowName = findViewById<EditText>(R.id.txt_window_name).text.toString()
//        // Display a message
//        Toast.makeText(this, "You choose $windowName", Toast.LENGTH_LONG).show()
//
//        // Starting a intent to launch another activity
//        val intent = Intent(this, WindowActivity::class.java).apply {
//            putExtra(WINDOW_NAME_PARAM, windowName)
//        }
//        startActivity(intent)
//    }


    fun openBuildingActivity(view: View) {
        startActivity(
            Intent(this, BuildingList::class.java)
        )
    }
    fun openWindowsActivity(view: View) {
        startActivity(
            Intent(this, WindowsActivity::class.java)
        )
    }
    fun openRoomsActivity(view: View) {
        startActivity(
            Intent(this, RoomsActivity::class.java)
        )
    }
    fun openCreateRoomsActivity(view: View) {
        startActivity(
            Intent(this, CreateRoomActivity::class.java)
        )
    }
    fun openHeaterActivity(view: View) {
        startActivity(
            Intent(this, HeatersActivity::class.java)
        )
    }
}