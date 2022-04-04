package com.example.uiassignment.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.uiassignment.api.GovService
import com.example.uiassignment.api.SiteDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SiteRepository(
    private val ioDispatcher: CoroutineDispatcher,
    private val govService: GovService
) {

    private val _sites = MutableLiveData<List<SiteData>>()
    val sites: LiveData<List<SiteData>> = _sites

    suspend fun refreshSites() {
        withContext(Dispatchers.Main) {
            _sites.value = fetchSites().map { SiteData.fromSiteDto(it) }
        }
    }

    private suspend fun fetchSites(): List<SiteDto> = withContext(ioDispatcher) {
        try {
            govService.getSites().records
        } catch (e: Exception) {
            emptyList()
        }
    }
}