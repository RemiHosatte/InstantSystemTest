package com.example.instantsystemtest.ui.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.instantsystemtest.R
import com.example.instantsystemtest.api.json.Article
import com.example.instantsystemtest.databinding.ActivityDetailsArticleBinding
import com.example.instantsystemtest.ui.details.viewmodel.DetailsViewModel
import com.example.instantsystemtest.ui.details.viewmodel.DetailsViewModelFactory

class DetailsActivity : AppCompatActivity() {
    private val TAG = "DetailsActivity"
    lateinit var viewModel: DetailsViewModel
    private lateinit var binding : ActivityDetailsArticleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_details_article)
        val article = intent.getParcelableExtra<Article>("article")
        viewModel = ViewModelProvider(this, DetailsViewModelFactory(article)).get(DetailsViewModel::class.java)
        binding.viewModel = viewModel

        viewModel.articleLiveData.observe(this, {
            binding.article = it
            if (it.urlToImage == null){
                binding.imgArticle.visibility= View.GONE
            } else {
                Glide.with(binding.imgArticle.context).load(it.urlToImage).into(binding.imgArticle)
            }
        })

        binding.btMore.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.addCategory(Intent.CATEGORY_BROWSABLE)
                intent.data = Uri.parse(viewModel.articleLiveData.value?.url)
                startActivity(intent)
            }
        })

    }


}