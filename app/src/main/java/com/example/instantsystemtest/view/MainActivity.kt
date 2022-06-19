package com.example.instantsystemtest.view

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instantsystemtest.R
import com.example.instantsystemtest.api.json.Article
import com.example.instantsystemtest.databinding.ActivityMainBinding
import com.example.instantsystemtest.model.Repository
import com.example.instantsystemtest.viewmodel.MainViewModel
import com.example.instantsystemtest.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity(), ArticleRecyclerView.OnItemClickListener {
    private val TAG = "MainActivity"
    lateinit var viewModel: MainViewModel
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel = ViewModelProvider(this, ViewModelFactory(Repository())).get(MainViewModel::class.java)

        viewModel.articlesList.observe(this, {
            Log.i(TAG, "List show")
            val recyclerView = findViewById<RecyclerView>(R.id.recyclerview);
            val adapter = ArticleRecyclerView(it, this)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter
        })

        viewModel.errorStr.observe(this, {
            Log.e(TAG, getString(R.string.cannot_get_data, it))
        })
        viewModel.getListArticles()
    }

    override fun onItemClick(item: Article) {
        viewModel.onArticleSelected(item)
    }

}