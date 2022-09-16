package com.educode.reignchallenge.data.service

import com.educode.reignchallenge.data.source.RemoteDataSource
import com.educode.reignchallenge.data.toDomainArticle
import com.educode.reignchallenge.domain.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArticleServiceDataSource: RemoteDataSource {
    override suspend fun getArticles(): List<Article> {
        return withContext(Dispatchers.IO){
             ArticleClient.service.getArticles().hits.map {
                 it.toDomainArticle()
             }
        }
    }
}