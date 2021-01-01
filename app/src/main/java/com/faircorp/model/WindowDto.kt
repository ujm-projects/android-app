package com.faircorp.model

enum class Status { OPEN, CLOSED}

data class WindowDto(val id: Long=-1, val name: String?="", val room: RoomDto?= RoomDto(), val windowStatus: Status?=Status.CLOSED)