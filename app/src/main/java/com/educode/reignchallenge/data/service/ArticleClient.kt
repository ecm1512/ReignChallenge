package com.educode.reignchallenge.data.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ArticleClient {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://hn.algolia.com/api/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: ArticleService = retrofit.create(ArticleService::class.java)
}