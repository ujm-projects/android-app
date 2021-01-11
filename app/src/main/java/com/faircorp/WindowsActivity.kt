package com.faircorp

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast
import com.faircorp.`interface`.OnWindowSelectedListener
import com.faircorp.service.ApiServices
import com.faircorp.model.WindowAdapter
import com.faircorp.service.WindowService
import kotlinx.coroutines.*
const val ROOM_ID_PARAM = "0"
class WindowsActivity : BasicActivity() , OnWindowSelectedListener {

    val windowService = WindowService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_windows)
        val recyclerView = findViewById<RecyclerView>(R.id.list_windows) // (2)
        val adapter = WindowAdapter(this) // (3)

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

//        adapter.update(windowService.findAll()) // (4)
        //We replace with api service call to get data from service
//        runCatching { ApiServices().windowsApiService.findAll().execute() } // (1)
//            .onSuccess { adapter.update(it.body() ?: emptyList()) }  // (2)
//            .onFailure {
//                Toast.makeText(this, "Error on windows loading $it", Toast.LENGTH_LONG).show()  // (3)
//        }

        if(ROOM_ID_PARAM == "0") {
            GlobalScope.launch(context = Dispatchers.IO) {
                runCatching { ApiServices().windowsApiService.findAll().execute() }
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
                runCatching { ApiServices().windowsApiService.findAllByRoom(ROOM_ID_PARAM.toLong()).execute() }
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
        }




//LifeCycleScope is not defined WWHYYYYYYYYYYYYYYYYYYY
//        lifecycleScope.launch(context = Dispatchers.IO) { // (1)
//            runCatching { ApiServices().windowsApiService.findAll().execute() } // (2)
//                    .onSuccess {
//                        withContext(context = Dispatchers.Main) { // (3)
//                            adapter.update(it.body() ?: emptyList())
//                        }
//                    }
//                    .onFailure {
//                        withContext(context = Dispatchers.Main) { // (3)
//                            Toast.makeText(
//                                    applicationContext,
//                                    "Error on windows loading $it",
//                                    Toast.LENGTH_LONG
//                            ).show()
//                        }
//                    }
//        }


    }

    override fun onWindowSelected(id: Long) {
        val intent = Intent(this, WindowActivity::class.java).putExtra(WINDOW_NAME_PARAM, id)
        startActivity(intent)
    }
}