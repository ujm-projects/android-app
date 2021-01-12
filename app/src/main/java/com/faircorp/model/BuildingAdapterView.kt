package com.faircorp.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.R
import com.faircorp.`interface`.OnBuildingSelectedListener

class BuildingAdapterView(val listener: OnBuildingSelectedListener) :
    RecyclerView.Adapter<BuildingAdapterView.BuildingViewHolder>(){

    inner class BuildingViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.txt_building_name)
        val outdootTemp: TextView = view.findViewById(R.id.txt_outdoor_temp)

    }

    private val items = mutableListOf<BuildingDto>()

    fun update(building: List<BuildingDto>) {
        items.clear()
        items.addAll(building)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size // (5)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuildingViewHolder { // (6)
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_building_item, parent, false)
        return BuildingViewHolder(view)
    }

    override fun onBindViewHolder(holder: BuildingViewHolder, position: Int) {  // (7)
        val building = items[position]
        holder.apply {
            name.text = building.name
            outdootTemp.text = building.outsideTemperature.toString() +" : ℃"
//            noOfRoom.text = building.room.s
            itemView.setOnClickListener { listener.onBuildingSelected(building.id) }
        }
    }
    override fun onViewRecycled(holder: BuildingViewHolder) { // (2)
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }

    }
}