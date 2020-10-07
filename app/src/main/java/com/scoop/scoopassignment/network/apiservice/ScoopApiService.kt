package com.scoop.scoopassignment.network.apiservice




import com.scoop.scoopassignment.internal.Utility.METHOD
import com.scoop.scoopassignment.network.response.SearchResult
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.QueryMap


interface ScoopApiService {

    // Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)-------Get(Select)



    @GET("${METHOD}album.search")
    fun fetchAlbumSearch(@QueryMap queryParameterMap: Map<String, String>): Deferred<SearchResult>









}






