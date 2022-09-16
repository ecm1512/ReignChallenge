package com.educode.reignchallenge.domain.usecases

import com.educode.reignchallenge.data.repository.ArticleRepository
import com.educode.reignchallenge.domain.Article

class GetArticles(private val articleRepository: ArticleRepository) {
    suspend fun invoke(): List<Article>{
        return articleRepository.getArticles()
    }
}