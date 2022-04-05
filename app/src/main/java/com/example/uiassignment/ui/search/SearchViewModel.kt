package com.example.uiassignment.ui.search

import androidx.lifecycle.*
import com.example.uiassignment.data.SiteData
import com.example.uiassignment.data.SiteRepository
import com.example.uiassignment.ui.site.SiteItem
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: SiteRepository) : ViewModel() {

    private val _resultItems = MutableLiveData<List<SiteItem.Site>>(emptyList())
    val resultItems: LiveData<List<SiteItem.Site>> = _resultItems

    private val _isSearching = MutableLiveData(false)
    val isSearching: LiveData<Boolean> = _isSearching

    val isEmpty: LiveData<Boolean> = Transformations.map(_resultItems) {
        it.isEmpty()
    }

    private val _queryText = MutableLiveData("")
    val queryText: LiveData<String> = _queryText

    private var searchJob: Job? = null

    fun onSearchQueryChanged(query: String) {
        val newQuery = query.trim()
        if (_queryText.value != newQuery) {
            _queryText.value = newQuery
            executeSearch(newQuery)
        }
    }

    private fun executeSearch(query: String) {
        // Cancel any in-flight searches
        searchJob?.cancel()

        _isSearching.value = true

        if (query.isEmpty()) {
            clearSearchResults()
            return
        }

        searchJob = viewModelScope.launch {
            delay(100)
            processSearchResult(repository.sites.value?.filter { it.name.contains(query) || it.county.contains(query) } ?: emptyList())
        }
    }

    private fun clearSearchResults() {
        _resultItems.value = emptyList()
        _isSearching.value = false
    }

    private fun processSearchResult(searchResult: List<SiteData>) {
        _resultItems.value = searchResult.map { createResultItem(it) }
        _isSearching.value = false
    }

    private fun createResultItem(from: SiteData): SiteItem.Site {
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