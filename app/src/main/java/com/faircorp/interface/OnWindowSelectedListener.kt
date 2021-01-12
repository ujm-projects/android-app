package com.faircorp.`interface`

import android.widget.Switch

interface OnWindowSelectedListener {
    fun onWindowSelected(id: Long)
    fun onWindowStatusSwitch(id: Long, isChecked: Boolean, switch: Switch)
}