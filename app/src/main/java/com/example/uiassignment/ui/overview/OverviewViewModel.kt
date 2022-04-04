package com.example.uiassignment.ui.overview

import androidx.lifecycle.*
import com.example.uiassignment.data.SiteData
import com.example.uiassignment.data.SiteRepository
import com.example.uiassignment.ui.site.SiteItem
import kotlinx.coroutines.launch

class OverviewViewModel(private val repository: SiteRepository) : ViewModel() {

    companion object {
        private const val TOP_SITE_SIZE = 10
    }

    private val _forceUpdate = MutableLiveData(false)

    private val _allItems = _forceUpdate.switchMap { forceUpdate ->
        if (forceUpdate) {
            _isLoading.value = true
            viewModelScope.launch {
                repository.refreshSites()
                _isLoading.value = false
            }
        }
        repository.sites.distinctUntilChanged().switchMap {
            MutableLiveData<List<SiteData>>().apply {
                value = it.sortedBy { it.pm2_5 }
            }
        }
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val topSiteItems: LiveData<List<SiteItem.Card>> = Transformations.map(_allItems) {
        it.take(TOP_SITE_SIZE).map { createTopSiteItem(it) }
    }

    val siteItems: LiveData<List<SiteItem.Site>> = Transformations.map(_allItems) {
        it.takeLast(maxOf(0, it.size - TOP_SITE_SIZE)).map { createSiteItem(it) }
    }

    val isEmpty: LiveData<Boolean> = Transformations.map(_allItems) {
        it.isEmpty()
    }

    init {
        loadData(true)
    }

    fun loadData(forceUpdate: Boolean) {
        _forceUpdate.value = forceUpdate
    }

    private fun createTopSiteItem(from: SiteData): SiteItem.Card {
        return SiteItem.Card(
            from.id.toString(),
            from.name,
            from.county,
            from.pm2_5.toString(),
            from.status
        )
    }

    private fun createSiteItem(from: SiteData): SiteItem.Site {
        return SiteItem.Site(
            from.id.toString(),
            from.name,
            from.county,
            from.pm2_5.toString(),
            from.status,
            from.isGoodStatus
        )
    }
}