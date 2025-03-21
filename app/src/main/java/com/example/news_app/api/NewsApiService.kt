package com.example.news_app.api

import com.example.news_app.model.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/everything")
    fun everything(
        @Query("q") query: String,
        @Query("searchIn") searchIn: String = "title",
        @Query("sortBy") sortBy: String = "publishedAt",
        @Query("apiKey") apiKey: String
    ): Call<NewsResponse>
}