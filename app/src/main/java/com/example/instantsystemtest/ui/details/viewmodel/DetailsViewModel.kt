package com.example.instantsystemtest.ui.details.viewmodel

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.instantsystemtest.api.json.Article
import kotlinx.coroutines.*


public class DetailsViewModel(private val article: Article?) : ViewModel() {
    private val TAG = "DetailsViewModel"

    val articleLiveData = MutableLiveData<Article>()


    init {
        articleLiveData.postValue(article)
    }


}