package com.faircorp.model

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiServices {

        val api_url="https://rest-api.cleverapps.io/api/"
        val windowsApiService : WindowApiService by lazy {
            Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(api_url)
                .build()
                .create(WindowApiService::class.java)
        }

}