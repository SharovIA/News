package com.ivanasharov.news.models

data class News(
    val title: String,
    val description: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)