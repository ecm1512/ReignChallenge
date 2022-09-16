package com.educode.reignchallenge.domain.usecases

import com.educode.reignchallenge.data.repository.ArticleRepository
import com.educode.reignchallenge.domain.Article

class DeleteArticle(private val articleRepository: ArticleRepository) {
    suspend fun invoke(article: Article){
        articleRepository.deleteArticle(article)
    }
}