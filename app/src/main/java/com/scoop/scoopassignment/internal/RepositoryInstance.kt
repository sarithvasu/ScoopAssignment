package com.scoop.scoopassignment.internal

import com.scoop.scoopassignment.repository.ScoopRepositoryImpl
import com.scoop.scoopassignment.network.apiservice.ScoopApiService
import com.scoop.scoopassignment.network.datasource.ScoopDataSourceImpl

class RepositoryInstance {

    companion object {
        val scoopRepositoryInstance: ScoopRepositoryImpl by lazy {
            val apiService= AbstractRetrofit.buildService(ScoopApiService::class.java)
            val buyerDataSource = ScoopDataSourceImpl(apiService)
            ScoopRepositoryImpl(buyerDataSource)
        }


    }
}
