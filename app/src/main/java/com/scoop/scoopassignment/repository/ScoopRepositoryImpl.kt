package com.scoop.scoopassignment.repository

import androidx.lifecycle.LiveData
import com.dinerindia.buyer.network.datasource.ScoopDataSource
import com.scoop.scoopassignment.network.response.SearchResult


import kotlinx.coroutines.*

class ScoopRepositoryImpl(private val scoopDataSource: ScoopDataSource) : ScoopRepository {

    // ------------------------------------------------------Get(Select)------------------------------------------------
    override suspend fun fetchSearchResult(queryParameterMap: Map<String, String>): LiveData<out SearchResult> {

        return withContext(Dispatchers.IO) {
            scoopDataSource.fetchHomeFeed(queryParameterMap)
            return@withContext scoopDataSource.downloadedHomeFeed
        }
    }




}