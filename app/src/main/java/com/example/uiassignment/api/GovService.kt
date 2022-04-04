package com.example.uiassignment.api

import retrofit2.http.GET

interface GovService {

    @GET("api/v1/aqx_p_432?limit=1000&api_key=9be7b239-557b-4c10-9775-78cadfc555e9&sort=ImportDate%20desc&format=json")
    suspend fun getSites(): GovApiResponse
}