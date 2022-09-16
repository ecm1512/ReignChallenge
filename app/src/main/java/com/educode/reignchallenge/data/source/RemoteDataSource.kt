package com.educode.reignchallenge.data.source

import com.educode.reignchallenge.domain.Article

interface RemoteDataSource {
    suspend fun getArticles(): List<Article>
}