package com.ivanasharov.news.client

import com.ivanasharov.news.client.dto.NewsServer
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiDefinition {
    @GET("category/science/in.json")
    fun getNews(): Deferred<NewsServer>
}