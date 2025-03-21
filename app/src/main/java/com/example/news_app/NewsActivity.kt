package com.example.news_app

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news_app.databinding.ActivityNewsBinding
import com.example.news_app.model.Article
import com.example.news_app.utils.Constants

class NewsActivity : AppCompatActivity(),NewsView {
    private lateinit var binding: ActivityNewsBinding
    private lateinit var adapter: NewsAdapter
    private lateinit var presenter: NewsPresenter
    private var currentQuery: String = "биткоин"
    private val searchIn ="title"
    private val sortBy ="publishedAt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        presenter=NewsPresenter(this,this)

        adapter = NewsAdapter(
            onItemClick = {title,description,urlToImage ->
                val intent = Intent(this,ArticleActivity::class.java).apply {
                    putExtra("title", title)
                    putExtra("description",description)
                    putExtra("urlToImage",urlToImage)
                }
                startActivity(intent)
            }
        )
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        binding.swipeRefresher.setOnRefreshListener {
            presenter.loadNews(Constants.API_KEY,currentQuery,searchIn,sortBy)
        }

        presenter.loadSavedNews()
        //presenter.loadNews(Constants.API_KEY,currentQuery,searchIn,sortBy)
    }

    override fun showLoading() {
        binding.swipeRefresher.isRefreshing = true
    }

    override fun hideLoading() {
        binding.swipeRefresher.isRefreshing=false
    }

    override fun showNews(newsList: List<Article>) {
        adapter.submitList(newsList)
        presenter.saveNews(newsList)
        Toast.makeText(this,"test",Toast.LENGTH_LONG)
    }

    override fun showError(message: String) {
        Toast.makeText(this,message,Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (!query.isNullOrEmpty()){
                    currentQuery=query
                    presenter.loadNews(Constants.API_KEY,currentQuery,searchIn,sortBy)
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                return false
            }
        })
        return true
    }
}