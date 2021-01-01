package com.faircorp.model

import retrofit2.Call
import retrofit2.http.*

interface WindowApiService {
    @GET("window")
    fun findAll(): Call<List<WindowDto>>

    @GET("window/{id}")
    fun findById(@Path("id") id: Long): Call<WindowDto>

    @GET("window")
    fun findAll(@Query("sort") sort: String): Call<List<WindowDto>>

    @PUT("window/{id}")
    fun updateWindow(@Path("id") id: Long, @Body window: WindowDto): Call<WindowDto>
}