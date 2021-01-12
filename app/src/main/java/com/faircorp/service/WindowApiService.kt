package com.faircorp.service

import com.faircorp.model.WindowDto
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

    @GET("window/byRoom/{roomId}")
    fun findAllByRoom(@Path("roomId") id: Long): Call<List<WindowDto>>

    @PUT("window/{id}/switch-v2")
    fun swtichWindowStatus(@Path("id") id: Long,@Query("status") status: Int): Call<WindowDto>

}