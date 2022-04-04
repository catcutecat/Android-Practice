package com.example.uiassignment

import android.app.Application
import com.example.uiassignment.api.GovApi
import com.example.uiassignment.data.SiteRepository
import kotlinx.coroutines.Dispatchers

class MainApplication : Application() {

    // TODO: Consider a Dependency Injection framework.
    val siteRepository: SiteRepository by lazy {
        SiteRepository(Dispatchers.IO, GovApi.govService)
    }
}