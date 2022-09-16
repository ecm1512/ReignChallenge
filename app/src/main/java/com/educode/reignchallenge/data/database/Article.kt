package com.educode.reignchallenge.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Article (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val subtitle: String,
    val isVisible: Boolean,
    val web: String
)