package com.example.news_app

import android.content.Context
import com.example.news_app.api.RetrofitClient
import com.example.news_app.model.Article
import com.example.news_app.model.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query

class NewsPresenter(
    private val context: Context,
    private val view: NewsView
) {
    private val newsStorage = NewsStorage(context)

    fun loadNews(apiKey:String,query: String,searchIn: String, sortBy: String){
        view.showLoading()
        RetrofitClient.instance.everything(apiKey=apiKey, query = query, searchIn = searchIn, sortBy = sortBy)
            .enqueue( object :Callback<NewsResponse>{
                override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                    view.hideLoading()
                    if (response.isSuccessful){
                        response.body()?.articles?.let {
                            view.showNews(it)
                        }?: view.showError("Ошибка загрузки данных")
                    } else{
                        view.showError("Ошибка сервера:${response.code()}")
                    }
                }
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    view.showError("Ошибка сети. Попытка отобразить сохраненные новости")
                    loadSavedNews()
                }
            })
    }


    fun saveNews(newsList: List<Article>){
        if (newsList.isNotEmpty()){
            newsStorage.saveNews(newsList)
        }
    }

    fun loadSavedNews(){
        val newsList = newsStorage.getSavedNews()
        view.hideLoading()
        if (newsList.isNotEmpty()){
            view.showNews(newsList)
        }else{
            view.showError("Сохраненных новостей нет")
        }
    }
}