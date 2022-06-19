package com.example.instantsystemtest.model

import com.example.instantsystemtest.api.ApiUtils

open class Repository {

   fun getFrenchArticles() = ApiUtils.retrofitBuilder().getTopHeadLines("fr")

}