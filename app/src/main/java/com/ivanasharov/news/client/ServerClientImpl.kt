package com.ivanasharov.news.client

import com.google.gson.GsonBuilder
import com.ivanasharov.news.client.dto.Articles
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServerClientImpl : ServerClient {
    companion object{
        private const val URL = "https://saurav.tech/NewsAPI/top-headlines/"
    }

    val mService: ApiDefinition
    private lateinit var mListArticles: List<Articles>

    init {
        val gson = GsonBuilder().create()

        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(URL)
            .build()
        mService = retrofit.create(ApiDefinition::class.java)
    }

    override suspend fun getServerNews(): List<Articles> {
        mService.getNews().await().let{ response->
           mListArticles = response.articles
        }
        return mListArticles

    }
}