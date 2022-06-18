package com.example.instantsystemtest.api

import com.example.instantsystemtest.api.json.NewsResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
        @GET("top-headlines")
        fun getTopHeadLines(@Query("language") language : String) : Call<NewsResponse>;
        

}