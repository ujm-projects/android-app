package com.faircorp.service

import com.faircorp.model.HeaterDto
import com.faircorp.model.WindowDto
import retrofit2.Call
import retrofit2.http.*

interface HeaterApiService {
    @GET("heater")
    fun findAll(): Call<List<HeaterDto>>

    @GET("heater/{id}")
    fun findById(@Path("id") id: Long): Call<HeaterDto>

    @GET("heater")
    fun findAll(@Query("sort") sort: String): Call<List<HeaterDto>>

    @PUT("heater/{id}/switch")
    fun switchHeater(@Path("id") id: Long, @Query("status") status : Int): Call<HeaterDto>


    @POST("heater")
    fun create( @Body window: WindowDto): Call<HeaterDto>

    @GET("heater/byRoom/{roomId}")
    fun findAllByRoom(@Path("roomId") id: Long): Call<List<HeaterDto>>


}