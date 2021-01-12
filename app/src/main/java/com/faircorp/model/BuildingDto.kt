package com.faircorp.model


data class BuildingDto(val id: Long=-1,
                   val name: String="",
                   val outsideTemperature: Double?=0.0){

    override fun toString(): String {
        return name
    }
}



