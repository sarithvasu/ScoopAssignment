package com.scoop.scoopassignment.repository

import androidx.lifecycle.LiveData
import com.scoop.scoopassignment.network.response.SearchResult

interface ScoopRepository {
    // Network------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)
    suspend fun fetchSearchResult(queryParameterMap: Map<String, String>): LiveData<out SearchResult>

}