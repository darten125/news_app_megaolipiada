package com.example.news_app

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news_app.databinding.ItemNewsBinding
import com.example.news_app.model.Article

class NewsAdapter(
    private val onItemClick: (String,String?,String?) -> Unit
):RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    private val newsList = mutableListOf<Article>()

    fun submitList(list:List<Article>){
        newsList.clear()
        newsList.addAll(list)
        notifyDataSetChanged()
    }

    class NewsViewHolder(
        private val binding: ItemNewsBinding,
        private val onItemClick: (String,String?,String?) -> Unit
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(article: Article){
            binding.titleTextView.text = article.title
            binding.desriptionTextView.text = article.description
            if (!article.urlToImage.isNullOrEmpty()){
                Glide.with(binding.ImageView.context)
                    .load(article.urlToImage)
                    .into(binding.ImageView)
            }

            itemView.setOnClickListener {
                onItemClick(article.title,article.description,article.urlToImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NewsViewHolder(binding,onItemClick)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    override fun getItemCount(): Int = newsList.size

}