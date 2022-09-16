package com.educode.reignchallenge.domain

data class Article(
    val id: Int,
    val title: String,
    val subtitle: String,
    var isVisible: Boolean,
    val web: String?
)