
package com.faircorp.`interface`

import android.widget.Switch

interface OnHeaterEventListner {
    fun onHeaterSelected(id: Long)
    fun onHeaterSwitchChange(id:Long, status:Boolean, switch: Switch)
}