package com.faircorp.`interface`

interface OnRoomSelectedListner {
    fun onRoomSelected(id: Long)
    fun onRoomHeaterSwitchClicked(id: Long, isChecked: Boolean)
    fun onRoomWindowSwitchClicked(id: Long, isChecked: Boolean)
    fun onRoomShowHeaterClicked(id: Long)
    fun onRoomShowWindowsClicked(id: Long)
}