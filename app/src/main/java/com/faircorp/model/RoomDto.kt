package com.faircorp.model

data class RoomDto(val id: Long=-1,
                   val name: String="",
                   val floor: String?="",
                   val currentTemperature: Double?=0.0,
                   val targetTemperature: Double?=0.0,
                   val noOfOpenWindow:Int?=0 ,
                   val noOfOnHeater:Int?=0,
                   val buildingId:Long?=-1 )