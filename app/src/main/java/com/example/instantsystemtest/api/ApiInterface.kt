package com.example.instantsystemtest.api

import com.example.instantsystemtest.api.json.NewsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
        @GET("/top-headlines?language=fr")
        suspend fun getFrenchNews() : Response<NewsResponse>;
        

}