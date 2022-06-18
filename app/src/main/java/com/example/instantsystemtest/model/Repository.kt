package com.example.instantsystemtest.model

import androidx.lifecycle.LiveData
import com.example.instantsystemtest.api.ApiUtils
import com.example.instantsystemtest.api.json.Articles
import com.example.instantsystemtest.api.json.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository {

   fun getFrenchArticles() = ApiUtils.retrofitBuilder().getTopHeadLines("fr")

}