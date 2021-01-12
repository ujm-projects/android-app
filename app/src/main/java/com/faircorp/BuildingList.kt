package com.faircorp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.`interface`.OnBuildingSelectedListener
import com.faircorp.`interface`.OnWindowSelectedListener
import com.faircorp.model.BuildingAdapterView
import com.faircorp.service.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BuildingList : BasicActivity() , OnBuildingSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_building_list)

        val recyclerView = findViewById<RecyclerView>(R.id.building_list)
        val adapter = BuildingAdapterView(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        GlobalScope.launch (context = Dispatchers.IO) {
            runCatching { ApiServices().buildingApiService.findAll().execute() }
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

    override fun onBuildingSelected(id: Long) {
        val intent =  Intent(this, RoomsActivity::class.java).putExtra(BUILDING_ID_PARAM,id);
         startActivity(intent)
    }
}