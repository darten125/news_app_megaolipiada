package com.example.news_app

import com.example.news_app.model.Article

interface NewsView {
    fun showLoading()
    fun hideLoading()
    fun showNews(newsList: List<Article>)
    fun showError(message:String)
}