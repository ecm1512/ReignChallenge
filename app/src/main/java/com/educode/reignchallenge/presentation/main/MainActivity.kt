package com.educode.reignchallenge.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.educode.reignchallenge.databinding.ActivityMainBinding
import com.educode.reignchallenge.presentation.webview.WebViewActivity
import org.koin.androidx.scope.ScopeActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ScopeActivity() {

    private val viewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.loadArticles()

        articleAdapter = ArticleAdapter{ web->
            goToWebView(web)
        }

        binding.swipeLayout.setOnRefreshListener{
            viewModel.loadArticles()
        }

        listenData()

        val swipeToDeleteCallback = object : SwipeToDeleteCallback(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                viewModel.deleteArticle(position)
                binding.recyclerArticles.adapter?.notifyItemRemoved(position)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerArticles)

    }

    private fun goToWebView(web: String) {
        val intent = Intent(this,WebViewActivity::class.java)
        intent.putExtra("web",web)
        startActivity(intent)
    }

    private fun listenData(){
        viewModel.articles.observe(this) {
            articleAdapter.articles = it
        }

        binding.recyclerArticles.adapter = articleAdapter

        viewModel.loading.observe(this){
            binding.swipeLayout.isRefreshing = it
        }
    }
}