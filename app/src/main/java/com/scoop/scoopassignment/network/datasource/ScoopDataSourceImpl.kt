@file:Suppress("SpellCheckingInspection")

package com.scoop.scoopassignment.network.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dinerindia.buyer.network.datasource.ScoopDataSource

import com.scoop.scoopassignment.network.apiservice.ScoopApiService
import com.scoop.scoopassignment.internal.NoConnectivityException
import com.scoop.scoopassignment.network.response.SearchResult

class ScoopDataSourceImpl(private val scoopApiService: ScoopApiService) : ScoopDataSource {

    // ------------------------------------------------------Get(Select)------------------------------------------------
    // Get Home Feed from Server
    private val _downloadedHomeFeed = MutableLiveData<SearchResult>()
    override val downloadedHomeFeed: LiveData<SearchResult>
        get() = _downloadedHomeFeed

    override suspend fun fetchHomeFeed(queryParameterMap: Map<String, String>) {
        try {
            val fetchedHomeFeed = scoopApiService.fetchAlbumSearch(queryParameterMap).await()
            _downloadedHomeFeed.postValue(fetchedHomeFeed)

        }catch (ex: NoConnectivityException){
            Log.e("Connectivity", "Soumen No internet connection.", ex)
        }
    }



}