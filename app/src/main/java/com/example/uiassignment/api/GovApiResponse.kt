package com.example.uiassignment.api

import com.google.gson.annotations.SerializedName

data class GovApiResponse(
    val records: List<SiteDto>
)

data class SiteDto(
    @SerializedName("SiteId")
    val id: Int,
    @SerializedName("SiteName")
    val name: String,
    @SerializedName("County")
    val county: String,
    @SerializedName("PM2.5")
    val pm2_5: Int,
    @SerializedName("Status")
    val status: String,
)
