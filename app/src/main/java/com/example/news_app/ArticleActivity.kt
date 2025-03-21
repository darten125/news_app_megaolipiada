package com.example.news_app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.news_app.databinding.ActivityArticleBinding

class ArticleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val articleData = getIntent().extras
        binding.titleTextView.text = articleData?.get("title").toString()
        binding.desriptionTextView.text = articleData?.get("description").toString()
        val urlToImage = articleData?.get("urlToImage").toString()
        if (!urlToImage.isNullOrEmpty()){
            Glide.with(binding.articleImageView.context)
                .load(urlToImage)
                .into(binding.articleImageView)
        }
    }
}