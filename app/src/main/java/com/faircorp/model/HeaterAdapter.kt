package com.faircorp.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.R
import com.faircorp.`interface`.OnHeaterEventListner

class HeaterAdapter(val listener: OnHeaterEventListner) : RecyclerView.Adapter<HeaterAdapter.HeaterViewHolder>() {

    inner class HeaterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.txt_heater_name)
        val status: TextView = view.findViewById(R.id.txt_heater_status)
        val btnHeaterStatus: Switch = view.findViewById(R.id.swt_heater_status)
    }

    private val items = mutableListOf<HeaterDto>()
    fun update(heater: List<HeaterDto>) {
        items.clear()
        items.addAll(heater)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_heaters_item, parent, false)
        return HeaterViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaterViewHolder, position: Int) {
        val heater = items[position]
        holder.apply {
            name.text = heater.name
            status.text = heater.heaterStatus.toString()
//            room.text = window.room.name
            itemView.setOnClickListener { listener.onHeaterSelected(heater.id) }
            btnHeaterStatus.setOnClickListener{listener.onHeaterSwitchChange(heater.id,btnHeaterStatus.isChecked)}
        }
    }
    override fun onViewRecycled(holder: HeaterViewHolder) {
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }
    }
}