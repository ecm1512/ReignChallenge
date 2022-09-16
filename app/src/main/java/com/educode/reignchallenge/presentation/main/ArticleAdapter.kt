package com.educode.reignchallenge.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.educode.reignchallenge.databinding.ItemArticleBinding
import com.educode.reignchallenge.domain.Article
import kotlin.properties.Delegates

class ArticleAdapter(private var listenerArticleItem: (String) -> Unit): RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    var articles: List<Article> by Delegates.observable(emptyList()){ _, old, new ->
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
            ArticleItemDiffCallback(old,new)
        )
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = ItemArticleBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = articles[position]
        holder.itemView.setOnClickListener {
            listenerArticleItem(article.web!!)
        }
        holder.setDataCard(article)
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class ArticleViewHolder(private val binding:ItemArticleBinding): RecyclerView.ViewHolder(binding.root) {
        fun setDataCard(article: Article){
            binding.itemArticleName.text = article.title
            binding.itemArticleSubtitle.text = article.subtitle
        }
    }

    class ArticleItemDiffCallback(
        var oldArticleList: List<Article>,
        var newArticleList: List<Article>
    ): DiffUtil.Callback(){
        override fun getOldListSize(): Int {
            return oldArticleList.size
        }

        override fun getNewListSize(): Int {
            return newArticleList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldArticleList[oldItemPosition].id == newArticleList[newItemPosition].id)
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldArticleList[oldItemPosition].equals(newArticleList[newItemPosition])
        }

    }
}