package com.example.instantsystemtest.ui.main.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instantsystemtest.api.json.Article
import com.example.instantsystemtest.api.json.NewsResponse
import com.example.instantsystemtest.model.Repository
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


public class MainViewModel(private val repository: Repository) : ViewModel() {
    private val TAG = "MainViewModel"

    val articlesList = MutableLiveData<List<Article>>()
    val errorStr = MutableLiveData<String>()

    val handler = CoroutineExceptionHandler { _, exception ->
        Log.e(TAG, "CoroutineExceptionHandler got $exception")
    }

    fun getListArticles() {
        CoroutineScope(Dispatchers.IO).launch(handler) {
            repository.getFrenchArticles().enqueue(object : Callback<NewsResponse?> {
                override fun onResponse(
                    call: Call<NewsResponse?>, response: Response<NewsResponse?>
                ) {
                    if (response.isSuccessful) {
                        articlesList.postValue(response.body()?.articles)
                    } else {
                        errorStr.postValue(response.code().toString())
                    }
                }

                override fun onFailure(call: Call<NewsResponse?>, t: Throwable) {
                    Log.e(TAG, "onFailure: " + t.stackTrace)
                    errorStr.postValue("Server error");
                }
            })

        }
    }



}