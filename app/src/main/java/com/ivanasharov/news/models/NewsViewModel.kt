package com.ivanasharov.news.models

import com.ivanasharov.news.data.database.NewsData
import java.io.Serializable

data class NewsViewModel(val news: NewsData,
                         val title: String = news.title,
                         val description: String? = news.description,
                         val urlToImage: String? = news.urlToImage,
                         val publishedAt: String? = news.publishedAt,
                         val content: String? = news.content
): Serializable