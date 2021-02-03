package com.ivanasharov.news.repositories

import com.ivanasharov.news.data.database.NewsData
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun loadNews(): Boolean

    fun getAllNews(): Flow<List<NewsData>>
}