package com.educode.reignchallenge.data.repository

import com.educode.reignchallenge.data.source.LocalDataSource
import com.educode.reignchallenge.data.source.RemoteDataSource
import com.educode.reignchallenge.domain.Article

class ArticleRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    suspend fun getArticles(): List<Article> {
        if(localDataSource.isEmpty()){
            val articles = remoteDataSource.getArticles()
            localDataSource.saveArticlesInDatabase(articles)
        }
        return localDataSource.getArticles()

       // return listOf(Article(1,"PRUEBITA","",false))
    }

    suspend fun deleteArticle(article: Article){
        return localDataSource.deleteArticle(article)
    }
}