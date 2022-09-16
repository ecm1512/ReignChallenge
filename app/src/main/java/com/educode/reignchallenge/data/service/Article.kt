package com.educode.reignchallenge.data.service

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity
data class Article(
    @SerializedName("story_id")
    val id: Int,
    @SerializedName("story_title")
    val storyTitle: String?,
    val title: String?,
    @SerializedName("created_at")
    val createdAt: String,
    val author: String,
    @SerializedName("story_url")
    val web: String?
)
