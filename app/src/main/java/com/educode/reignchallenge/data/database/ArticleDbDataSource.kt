package com.educode.reignchallenge.data.database

import com.educode.reignchallenge.data.source.LocalDataSource
import com.educode.reignchallenge.data.toDomainArticle
import com.educode.reignchallenge.data.toRoomArticle
import com.educode.reignchallenge.domain.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArticleDbDataSource(db: ArticleDatabase): LocalDataSource {

    private val articleDao = db.articleDao()

    override suspend fun getArticles(): List<Article> {
        return withContext(Dispatchers.IO){
            articleDao.getArticles().map {
                it.toDomainArticle()
            }
        }
    }

    override suspend fun deleteArticle(article: Article) {
        withContext(Dispatchers.IO){
            articleDao.deleteArticle(article.toRoomArticle())
        }
    }

    override suspend fun isEmpty(): Boolean {
        return withContext(Dispatchers.IO){
            articleDao.articleCount() <= 0
        }
    }

    override suspend fun saveArticlesInDatabase(articles: List<Article>) {
        withContext(Dispatchers.IO){
            articleDao.insertMovies(articles.map {
                it.toRoomArticle()
            })
        }
    }
}