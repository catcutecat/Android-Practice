package com.example.uiassignment.data

import com.example.uiassignment.api.SiteDto

data class SiteData(
    val id: Int,
    val name: String,
    val county: String,
    val pm2_5: Int,
    val status: String,
) {
    val isGoodStatus = status == "良好"

    companion object {
        fun fromSiteDto(from: SiteDto): SiteData {
            return SiteData(
                from.id,
                from.name,
                from.county,
                from.pm2_5,
                from.status
            )
        }
    }
}
