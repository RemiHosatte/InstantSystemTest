package com.example.instantsystemtest.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.instantsystemtest.R
import com.example.instantsystemtest.api.json.Article
import com.example.instantsystemtest.databinding.ActivityMainBinding
import com.example.instantsystemtest.model.Repository
import com.example.instantsystemtest.ui.details.DetailsActivity
import com.example.instantsystemtest.ui.main.viewmodel.MainViewModel
import com.example.instantsystemtest.ui.main.viewmodel.MainViewModelFactory

class MainActivity : AppCompatActivity(), ArticleRecyclerView.OnItemClickListener {
    private val TAG = "MainActivity"
    lateinit var viewModel: MainViewModel
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel = ViewModelProvider(this, MainViewModelFactory(Repository())).get(MainViewModel::class.java)

        viewModel.articlesList.observe(this, {
            Log.i(TAG, "List show")
            val recyclerView = findViewById<RecyclerView>(R.id.recyclerview);
            val adapter = ArticleRecyclerView(it, this)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter
            if (binding.swiperefresh.isRefreshing){
                binding.swiperefresh.isRefreshing = false;
            }
        })

        viewModel.errorStr.observe(this, {
            Log.e(TAG, getString(R.string.cannot_get_data, it))
            Toast.makeText(this, getString(R.string.cannot_get_data, it), Toast.LENGTH_SHORT).show()
        })
        viewModel.getListArticles()

        binding.swiperefresh.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                viewModel.getListArticles()
            }
        })
    }

    override fun onItemClick(item: Article) {
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("article", item)
        startActivity(intent)
    }

}