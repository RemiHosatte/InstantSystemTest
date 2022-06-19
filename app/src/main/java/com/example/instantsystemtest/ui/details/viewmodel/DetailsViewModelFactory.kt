package com.example.instantsystemtest.ui.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.instantsystemtest.api.json.Article

class DetailsViewModelFactory constructor(private val article: Article?) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            DetailsViewModel(this.article) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}