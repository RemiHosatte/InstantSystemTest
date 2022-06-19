package com.example.instantsystemtest

import androidx.lifecycle.Observer
import com.example.instantsystemtest.api.ApiInterface
import com.example.instantsystemtest.api.ApiUtils
import com.example.instantsystemtest.api.json.NewsResponse
import com.example.instantsystemtest.model.Repository
import junit.framework.TestCase
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response
import retrofit2.Retrofit

class ApiTest : TestCase() {

    @Test
    fun testOKGetTopHeadLines(): Response<NewsResponse> {
        //Get an instance of Retrofit
        val apiInterface : ApiInterface = ApiUtils.retrofitBuilder()
        val response = apiInterface.getTopHeadLines("fr",100).execute()

        //Check for error body
        val errorBody = response.errorBody()
        assert(errorBody == null)
        //Check for success body
        val responseWrapper = response.body()
        assert(responseWrapper != null)
        assert(response.code() == 200)
        return response
    }

    @Test
    fun testNOKGetTopHeadLines(): Response<NewsResponse> {
        //Get an instance of Retrofit
        val apiInterface : ApiInterface = ApiUtils.retrofitBuilder()
        val response = apiInterface.getTopHeadLines("",500).execute()

        //Check for error body
        val errorBody = response.errorBody()
        assert(errorBody != null)
        //Check for success body
        val responseWrapper = response.body()
        assert(responseWrapper == null)
        assert(response.code() == 400)
        return response
    }

}