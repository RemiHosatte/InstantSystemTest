package com.example.instantsystemtest.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.instantsystemtest.R
import com.example.instantsystemtest.model.Repository
import com.example.instantsystemtest.viewmodel.MainViewModel
import com.example.instantsystemtest.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProvider(this, ViewModelFactory(Repository())).get(MainViewModel::class.java)

        viewModel.articlesList.observe(this, {
            Log.i(TAG, "List show")
        })

        viewModel.errorStr.observe(this, {
            Log.e(TAG, getString(R.string.cannot_get_data, it))
        })
        viewModel.getListArticles()
    }

}