package com.faircorp.model

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView
import com.faircorp.R
import com.faircorp.`interface`.OnWindowSelectedListener

class WindowAdapter(val listener: OnWindowSelectedListener) : RecyclerView.Adapter<WindowAdapter.WindowViewHolder>() { // (1)

    inner class WindowViewHolder(view: View) : RecyclerView.ViewHolder(view) { // (2)
        val name: TextView = view.findViewById(R.id.txt_windows_name)
        val room: TextView = view.findViewById(R.id.txt_window_room)
        val status: TextView = view.findViewById(R.id.txt_status)
        val floor:TextView=view.findViewById(R.id.txt_floow_wa)
        val swtWIndowStatus:Switch=view.findViewById(R.id.swt_window_status_wa)
    }

    private val items = mutableListOf<WindowDto>() // (3)

    fun update(windows: List<WindowDto>) {  // (4)
        items.clear()
        items.addAll(windows)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size // (5)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WindowViewHolder { // (6)
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.activity_windows_item, parent, false)
        return WindowViewHolder(view)
    }

    override fun onBindViewHolder(holder: WindowViewHolder, position: Int) {  // (7)
        val window = items[position]
        holder.apply {
            name.text = window.name
            status.text =window.windowStatus.toString()
            room.text ="ROOM :"+ window.room?.name
            floor.text="FLOOR :"+window.room?.floor
            if(window.windowStatus==Status.OPEN){
                swtWIndowStatus.text="CLOSE";
                swtWIndowStatus.setChecked(true);
            }else{
                swtWIndowStatus.text="OPEN";
                swtWIndowStatus.setChecked(false);
            }
            swtWIndowStatus.setOnClickListener{listener.onWindowStatusSwitch(window.id, swtWIndowStatus.isChecked, swtWIndowStatus)}
            itemView.setOnClickListener { listener.onWindowSelected(window.id) }
        }
    }
    override fun onViewRecycled(holder: WindowViewHolder) { // (2)
        super.onViewRecycled(holder)
        holder.apply {
            itemView.setOnClickListener(null)
        }

    }
}