package com.example.instantsystemtest.ui.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instantsystemtest.api.json.Article
import com.example.instantsystemtest.databinding.ArticleCardBinding

class ArticleViewHolder(val binding: ArticleCardBinding) : RecyclerView.ViewHolder(binding.root) {


    fun bind(item: Article, listener: ArticleRecyclerView.OnItemClickListener) {

        binding.article = item;
        if (item.urlToImage == null){
            binding.imgArticle.visibility= View.GONE
        } else {
            Glide.with(binding.imgArticle.context).load(item.urlToImage).into(binding.imgArticle)
        }
        itemView.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                listener.onItemClick(item)
            }
        })

    }
}