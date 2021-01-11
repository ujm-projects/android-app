package com.faircorp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.`interface`.OnRoomSelectedListner
import com.faircorp.`interface`.OnWindowSelectedListener
import com.faircorp.model.RoomAdapter
import com.faircorp.model.WindowAdapter
import com.faircorp.service.ApiServices
import com.faircorp.service.WindowService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

const val BUILDING_ID_PARAM = "0"
class RoomsActivity : BasicActivity() , OnRoomSelectedListner {
    val windowService = WindowService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms)
        val recyclerView = findViewById<RecyclerView>(R.id.list_rooms)
        val adapter = RoomAdapter(this)

        recyclerView.layoutManager =
            LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

        if(BUILDING_ID_PARAM.equals("0")){
            GlobalScope.launch(context = Dispatchers.IO) {
                runCatching { ApiServices().roomApiService.findAll().execute() }
                    .onSuccess {
                        withContext(context = Dispatchers.Main) {
                            adapter.update(it.body() ?: emptyList())
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
        }else{
            GlobalScope.launch(context = Dispatchers.IO) {
                runCatching { ApiServices().roomApiService.findAllByBuildingId(BUILDING_ID_PARAM.toLong()).execute() }
                    .onSuccess {
                        withContext(context = Dispatchers.Main) {
                            adapter.update(it.body() ?: emptyList())
                        }
                    }
                    .onFailure {
                        withContext(context = Dispatchers.Main) { // (3)
                            Toast.makeText(
                                applicationContext,
                                "Error on getting room list : $it",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
        }
    }

    override fun onRoomHeaterSwitchClicked(id: Long, isChecked: Boolean) {
        val status = if (isChecked) 0 else 1;
        GlobalScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().roomApiService.switchHeaters(id,status).execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        Toast.makeText(
                            applicationContext,
                            "ROOM HEATERS STATUS SUCCESSFULLY UPDATED",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) { // (3)
                        Toast.makeText(
                            applicationContext,
                            "Error on updating status : $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }

    override fun onRoomWindowSwitchClicked(id: Long, isChecked: Boolean) {
        val status = if (isChecked) 0 else 1;
        GlobalScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().roomApiService.switchWindows(id,status).execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        Toast.makeText(
                            applicationContext,
                            "ROOM WINDOWS STATUS SUCCESSFULLY UPDATED",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) { // (3)
                        Toast.makeText(
                            applicationContext,
                            "Error on updating status : $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }

    override fun onRoomSelected(id: Long) {

    }
    override fun onRoomShowHeaterClicked(id: Long) {
        TODO("Not yet implemented")
    }

    override fun onRoomShowWindowsClicked(id: Long) {
        val intent = Intent(this, WindowsActivity::class.java)
            .putExtra(ROOM_ID_PARAM, id.toString())
        startActivity(intent)
    }

}