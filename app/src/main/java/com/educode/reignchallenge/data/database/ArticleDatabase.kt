package com.educode.reignchallenge.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Article::class], version = 3)
abstract class ArticleDatabase : RoomDatabase() {
    companion object{
        fun build(context: Context) = Room.databaseBuilder(
            context,
            ArticleDatabase::class.java,
            "ArticlesDb"
        ).build()
    }

    abstract fun articleDao(): ArticleDao
}