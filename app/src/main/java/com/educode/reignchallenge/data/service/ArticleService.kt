package com.educode.reignchallenge.data.service

import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {

    @GET("search_by_date")
    suspend fun getArticles(
        @Query("query")
        query: String = "mobile"
    ): ArticlesResponse
}