package com.educode.reignchallenge.data.source

import com.educode.reignchallenge.domain.Article

interface LocalDataSource {
    suspend fun getArticles(): List<Article>
    suspend fun deleteArticle(article: Article)
    suspend fun isEmpty(): Boolean
    suspend fun saveArticlesInDatabase(articles: List<Article>)
}