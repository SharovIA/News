package com.ivanasharov.news.repositories

import com.ivanasharov.news.client.ServerClient
import com.ivanasharov.news.client.dto.Articles
import com.ivanasharov.news.data.database.NewsData
import com.ivanasharov.news.data.database.NewsDatabase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
        private val mServerClient: ServerClient,
        private val mNewsDatabase: NewsDatabase
): NewsRepository{

    override suspend fun loadNews(): Boolean {
        val listNews = loadListNews().map {
            NewsData(null, it.title, it.description, it.urlToImage, it.publishedAt, it.content)
        }
        return save(listNews)
    }

    override fun getAllNews(): Flow<List<NewsData>> = mNewsDatabase.getNewsDao().loadNews()

    private fun save(listNews: List<NewsData>): Boolean {
       val ids = mNewsDatabase.getNewsDao().insert(listNews)
        ids.forEach{
            if (it == null)
                return false
        }
        return true
    }

    private suspend fun loadListNews(): List<Articles> = mServerClient.getServerNews()
}