package com.dinerindia.buyer.network.datasource

import androidx.lifecycle.LiveData
import com.scoop.scoopassignment.network.response.SearchResult

interface ScoopDataSource {

    // Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)
    val downloadedHomeFeed : LiveData<SearchResult>
    suspend fun fetchHomeFeed(queryParameterMap: Map<String, String>)



}
