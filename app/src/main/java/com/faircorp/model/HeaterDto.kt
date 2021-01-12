package com.faircorp.model

    enum class HeaterStatus { ON, OFF}

    data class HeaterDto(val id: Long=-1, val name: String?="", val room: RoomDto?= RoomDto(), val heaterStatus: HeaterStatus?=HeaterStatus.OFF)
