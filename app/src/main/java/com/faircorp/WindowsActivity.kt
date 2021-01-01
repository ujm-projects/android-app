package com.faircorp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import androidx.lifecycle.coroutineScope
import com.faircorp.`interface`.OnWindowSelectedListener
import com.faircorp.model.ApiServices
import com.faircorp.model.WindowAdapter
import com.faircorp.service.WindowService
import androidx.lifecycle.lifecycleScope;
import kotlinx.coroutines.*

class WindowsActivity : BasicActivity() , OnWindowSelectedListener {
    val windowService = WindowService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_windows)
        val recyclerView = findViewById<RecyclerView>(R.id.list_windows) // (2)
        val adapter = WindowAdapter(this) // (3)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

//        adapter.update(windowService.findAll()) // (4)
        //We replace with api service call to get data from service
//        runCatching { ApiServices().windowsApiService.findAll().execute() } // (1)
//            .onSuccess { adapter.update(it.body() ?: emptyList()) }  // (2)
//            .onFailure {
//                Toast.makeText(this, "Error on windows loading $it", Toast.LENGTH_LONG).show()  // (3)
//        }

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