package com.faircorp.service

import com.faircorp.model.BuildingDto
import com.faircorp.model.WindowDto
import retrofit2.Call
import retrofit2.http.*

interface BuildingApiService {
    @GET("building")
    fun findAll(): Call<List<BuildingDto>>

    @GET("building/{id}")
    fun findById(@Path("id") id: Long): Call<BuildingDto>

    @DELETE("building/{id}")
    fun deleteBuilding(@Path("id") id: Long)

    @POST("building")
    fun create(@Body building: BuildingDto): Call<BuildingDto>

    @PUT("building/{id}")
    fun update(@Path("id") id: Long, @Body building: BuildingDto): Call<BuildingDto>
}