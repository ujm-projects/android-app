package com.faircorp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.`interface`.OnHeaterEventListner
import com.faircorp.model.HeaterAdapter
import com.faircorp.model.RoomAdapter
import com.faircorp.service.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
const val ROOM_ID_PARAM_ = "0"
class HeatersActivity : BasicActivity() , OnHeaterEventListner {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_heaters)

        val param = intent.getLongExtra(ROOM_ID_PARAM_,0)
        val recyclerView = findViewById<RecyclerView>(R.id.list_heaters)
        val adapter = HeaterAdapter(this)

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

        if(param.equals(0) ) {
            GlobalScope.launch(context = Dispatchers.IO) {
                runCatching { ApiServices().heaterApiServer.findAll().execute() }
                    .onSuccess {
                        withContext(context = Dispatchers.Main) {
                            adapter.update(it.body() ?: emptyList())
                        }
                    }
                    .onFailure {
                        withContext(context = Dispatchers.Main) { // (3)
                            Toast.makeText(
                                applicationContext,
                                "Error on heaters loading $it",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
        }else{
            GlobalScope.launch(context = Dispatchers.IO) {
                runCatching { ApiServices().heaterApiServer.findAllByRoom(param).execute() }
                    .onSuccess {
                        withContext(context = Dispatchers.Main) {
                            adapter.update(it.body() ?: emptyList())
                        }
                    }
                    .onFailure {
                        withContext(context = Dispatchers.Main) { // (3)
                            Toast.makeText(
                                applicationContext,
                                "Error on heaters loading $it",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
            }
        }
    }

    override fun onHeaterSelected(id: Long) {
        TODO("Not yet implemented")
    }

    override fun onHeaterSwitchChange(id: Long, status: Boolean) {
        val status=if(status) 1 else 0
        GlobalScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().heaterApiServer.switchHeater(id, status).execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        Toast.makeText(
                            applicationContext,
                            "Successfully status updated : ${it.body()?.heaterStatus.toString()}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                .onFailure {
                    withContext(context = Dispatchers.Main) { // (3)
                        Toast.makeText(
                            applicationContext,
                            "Error on heaters loading $it",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
        }
    }
}