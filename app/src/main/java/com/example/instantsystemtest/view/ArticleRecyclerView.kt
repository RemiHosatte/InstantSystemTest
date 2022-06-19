package com.example.instantsystemtest.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instantsystemtest.api.json.Article
import com.example.instantsystemtest.databinding.ArticleCardBinding

class ArticleRecyclerView(private val articles: List<Article>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ArticleCardBinding.inflate(inflater, parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(articles.get(position), listener)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    interface OnItemClickListener {
        fun onItemClick(item: Article)
    }
}

