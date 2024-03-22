package com.rendonsoft.apptestapi.commos.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.rendonsoft.apptestapi.commos.util.URL_BASE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network(url: String ?= null) {

    private fun interceptor() : HttpLoggingInterceptor {
        val loggingData = HttpLoggingInterceptor()
        loggingData.apply {
            level = HttpLoggingInterceptor.Level.HEADERS
            level = HttpLoggingInterceptor.Level.BASIC
            level = HttpLoggingInterceptor.Level.BODY
        }
        return loggingData
    }

    private val httpClient = OkHttpClient.Builder().apply {
        addInterceptor(interceptor())
    }

    private val retrofit = Retrofit
        .Builder()
        .baseUrl(url ?: URL_BASE)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .client(httpClient.build())
        .build()

    fun getInstance() = retrofit
}