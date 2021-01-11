package com.faircorp.service

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiServices {
//    https://rest-api.cleverapps.io/swagger-ui/index.html
        val api_url="https://rest-api.cleverapps.io/api/"
//      val api_url="http://localhost:8080/api/"
        val windowsApiService : WindowApiService by lazy {
            Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(api_url)
                .build()
                .create(WindowApiService::class.java)
        }
        val buildingApiService : BuildingApiService by lazy {
            Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(api_url)
                .build()
                .create(BuildingApiService::class.java)
        }
        val roomApiService : RoomApiService by lazy {
            Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(api_url)
                .build()
                .create(RoomApiService::class.java)
        }

}