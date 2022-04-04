package com.example.uiassignment.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GovApi {

    val govService = Retrofit.Builder()
        .baseUrl("https://data.epa.gov.tw/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(GovService::class.java)

}
