package com.educode.reignchallenge.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ArticleDao {

    @Query("SELECT * FROM Article WHERE isVisible = 1")
    fun getArticles(): List<Article>

    @Update
    fun deleteArticle(article: Article)

    @Query("SELECT COUNT(*) FROM Article")
    fun articleCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(articles: List<Article>)
}