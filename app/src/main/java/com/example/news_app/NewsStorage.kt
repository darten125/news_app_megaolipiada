package com.example.news_app

import android.content.Context
import com.example.news_app.model.Article
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NewsStorage(context: Context) {
    private val prefs = context.getSharedPreferences("news_prefs",Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveNews(newsList: List<Article>){
        val json = gson.toJson(newsList)
        prefs.edit().putString("saved_news",json).apply()
    }

    fun getSavedNews(): List<Article>{
        val json = prefs.getString("saved_news",null)?: return emptyList()
        val type = object: TypeToken<List<Article>>(){}.type
        return gson.fromJson(json,type)?: emptyList()
    }
}