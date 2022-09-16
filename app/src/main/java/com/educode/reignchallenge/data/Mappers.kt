package com.educode.reignchallenge.data

import com.educode.reignchallenge.data.database.Article

fun Article.toDomainArticle(): com.educode.reignchallenge.domain.Article = com.educode.reignchallenge.domain.Article(
    id,
    title,
    subtitle,
    isVisible,
    web
)

fun com.educode.reignchallenge.domain.Article.toRoomArticle(): Article = Article(
    id,
    title,
    subtitle,
    isVisible,
    web?:"www.google.com"
)

fun com.educode.reignchallenge.data.service.Article.toDomainArticle() = com.educode.reignchallenge.domain.Article(
    id,
    title?:storyTitle?:"",
    "$author - ${createdAt.toDateCreateAt()}",
    true,
    web?:"www.google.com"
)

fun String.toDateCreateAt(): String{
    return this.substring(0,10)
}