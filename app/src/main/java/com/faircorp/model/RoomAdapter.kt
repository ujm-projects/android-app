package com.faircorp.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.faircorp.R
import com.faircorp.`interface`.OnRoomSelectedListner

class RoomAdapter (val listener: OnRoomSelectedListner) : RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() { // (1)

    inner class RoomViewHolder(view: View) : RecyclerView.ViewHolder(view) { // (2)
        val name: TextView = view.findViewById(R.id.txt_room_name)
        val currentTemp: TextView = view.findViewById(R.id.txt_current_temp)
        val floor: TextView = view.findViewById(R.id.txt_floor)
        val heaterOn: TextView = view.findViewById(R.id.txt_heaters_on)
        val windowOpen: TextView = view.findViewById(R.id.txt_window_open)
        val switchWindow: Switch=view.findViewById(R.id.swt_window_room)
        val swtichHeater: Switch=view.findViewById(R.id.swt_heater_room)
    }

    private val items = mutableListOf<RoomDto>() // (3)

    fun update(room: List<RoomDto>) {  // (4)
        items.clear()
        items.addAll(room)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size // (5)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder { // (6)
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.room_list_item, parent, false)
        return RoomViewHolder(view)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {  // (7)
        val room = items[position]
        holder.apply {
            name.text = room.name
            currentTemp.text = room.currentTemperature.toString()
            floor.text = room.floor
//            heaterOn
//            windowOpen
//            room.text = window.room.name
            itemView.setOnClickListener { listener.onRoomSelected(room.id) }
            switchWindow.setOnClickListener{listener.onRoomWindowSwitchClicked(room.id, switchWindow.isChecked)}
            swtichHeater.setOnClickListener{listener.onRoomHeaterSwitchClicked(room.id, switchWindow.isChecked)}
        }
    }
    override fun onViewRecycled(holder: RoomViewHolder) { // (2)
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }

    }
}