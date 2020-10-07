package com.scoop.scoopassignment.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.scoop.scoopassignment.network.response.SearchResult
import com.scoop.scoopassignment.repository.ScoopRepository

class HomeViewModel(private val scoopRepository: ScoopRepository) : ViewModel() {
    suspend fun searchResult(queryParameterMap:Map<String,String>) : LiveData<out SearchResult> {
        return scoopRepository.fetchSearchResult(queryParameterMap)
    }
}