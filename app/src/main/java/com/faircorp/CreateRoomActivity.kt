package com.faircorp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.ListAdapter
import com.faircorp.model.BuildingDto
import com.faircorp.model.HeaterDto
import com.faircorp.model.RoomDto
import com.faircorp.service.ApiServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateRoomActivity : BasicActivity() {
    private val items = mutableListOf<BuildingDto>()
    private var selectedBuilding=BuildingDto();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_room)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val btnCreate:Button=findViewById<Button>(R.id.btn_create_room)

        val optionBuilding:Spinner=findViewById<Spinner>(R.id.sp_building)
        var buildingList:List<BuildingDto> =mutableListOf<BuildingDto>()
        val contx=this
        GlobalScope.launch(context = Dispatchers.IO) {
            runCatching { ApiServices().buildingApiService.findAll().execute() }
                .onSuccess {
                    withContext(context = Dispatchers.Main) {
                        items.clear()
                        val res=it.body()?: emptyList()
                        buildingList=res
                        optionBuilding.adapter=ArrayAdapter<BuildingDto>(
                            contx,
                            android.R.layout.simple_list_item_1,
                            buildingList
                        )
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
        var isSelected=false;
        optionBuilding.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    isSelected=false;
                }
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedBuilding=buildingList.get(position);
                    isSelected=true;
                }
        }
        btnCreate.setOnClickListener{
            val name = findViewById<EditText>(R.id.txt_room_name_cr).text.toString()
            val targetTemp = findViewById<EditText>(R.id.txt_target_temp_cr).text.toString().toDouble()
            val floor = findViewById<EditText>(R.id.txt_floor_name).text.toString()
            if(isSelected||!name.equals(null)||!targetTemp.equals(null)||!floor.equals(null) ){
             val room=RoomDto(0,name,floor, 0.0, targetTemp,0,0,selectedBuilding.id)

                GlobalScope.launch(context = Dispatchers.IO) {
                    runCatching { ApiServices().roomApiService.create(room).execute() }
                        .onSuccess {
                            withContext(context = Dispatchers.Main) {
                                Toast.makeText(
                                    applicationContext,
                                    "Successfully Created Room ID: ${it.body()?.id}",
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
            }else{
                Toast.makeText(
                    applicationContext,
                    "Please input all correct data as it showed in example text",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    }

}