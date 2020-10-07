package com.scoop.scoopassignment.internal

import android.util.Log
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.scoop.scoopassignment.interceptor.ConnectivityInterceptorImpl
import com.scoop.scoopassignment.internal.Utility.API_KEY
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class AbstractRetrofit {

    companion object {
        @Volatile
        private var instance: Retrofit? = null

        private const val BASE_URL="https://ws.audioscrobbler.com/2.0/"


        private val LOCK = Any()

        operator fun invoke() = instance ?: synchronized(LOCK) {
            instance ?: buildRetrofit().also { instance = it }
        }

        private fun buildRetrofit(): Retrofit {
            val headerInterceptor = Interceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .build()
                return@Interceptor chain.proceed(request)
            }

             val apiKeyInterceptor = Interceptor { chain ->
                val url = chain.request().url().newBuilder().addQueryParameter("api_key", API_KEY).addQueryParameter("format","json").build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()
                chain.proceed(request)
            }

             val httpLoggingInterceptor= HttpLoggingInterceptor {
                        Log.e("Logging", "log: http log: $it")
            }
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS

            val okHttpClient = OkHttpClient.Builder()

                .addInterceptor(ConnectivityInterceptorImpl())
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(5, TimeUnit.SECONDS)
                .addInterceptor(headerInterceptor)
                .addInterceptor(apiKeyInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun <T> buildService(serviceType: Class<T>): T {
            return invoke().create(serviceType)
        }
    }
}