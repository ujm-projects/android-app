package com.faircorp.service

import com.faircorp.model.BuildingDto
import com.faircorp.model.RoomDto
import com.faircorp.model.WindowDto
import retrofit2.Call
import retrofit2.http.*

interface RoomApiService {
    @GET("room")
    fun findAll(): Call<List<RoomDto>>

    @GET("room/{id}/building")
    fun findAllByBuildingId(@Path("id") id: Long): Call<List<RoomDto>>

    @GET("room/{id}")
    fun findById(@Path("id") id: Long): Call<RoomDto>

    @DELETE("room/{id}")
    fun deleteBuilding(@Path("id") id: Long) : Call<String>

    @POST("room")
    fun create(@Body building: RoomDto): Call<RoomDto>

    @PUT("room/{id}/switchHeatersStatus")
    fun switchHeatersStatus(@Path("id") id: Long) :Call<RoomDto>
    @PUT("room/{id}/switchWindowsStatus")
    fun switchWindowStatus(@Path("id") id: Long):Call<RoomDto>

    @PUT("room/{id}/switchHeaters")
    fun switchHeaters(@Path("id") id: Long, @Query("status") status: Int):Call<RoomDto>
    @PUT("room/{id}/switchWindows")
    fun switchWindows(@Path("id") id: Long, @Query("status") status: Int):Call<RoomDto>

}