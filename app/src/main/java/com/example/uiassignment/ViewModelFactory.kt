package com.example.uiassignment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.uiassignment.data.SiteRepository
import com.example.uiassignment.ui.overview.OverviewViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: Any) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = with(modelClass) {
        when {
            isAssignableFrom(OverviewViewModel::class.java) ->
                OverviewViewModel(repository as SiteRepository)
            else ->
                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    } as T
}
