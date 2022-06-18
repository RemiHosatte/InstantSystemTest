package com.example.instantsystemtest.api

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ApiUtils {

    private val url = "https://newsapi.org/v2/"
    private val apiKey = ""

    fun retrofitBuilder(): ApiInterface {
        return Retrofit.Builder()
            .baseUrl(url)
            .client(addInterceptorHTTP())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    private fun addInterceptorHTTP(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addInterceptor { chain: Interceptor.Chain ->
                val newRequest: Request =
                    chain.request().newBuilder().addHeader("Authorization", "Bearer " + apiKey)
                        .build()
                chain.proceed(newRequest)
            }
            .build()
    }
}