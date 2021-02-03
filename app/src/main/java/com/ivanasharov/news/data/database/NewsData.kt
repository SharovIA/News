package com.ivanasharov.news.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsData(
        @PrimaryKey(autoGenerate = true)
        val id : Long?,
        val title: String,
        val description: String?,
        val urlToImage: String?,
        val publishedAt: String?,
        val content: String?
)