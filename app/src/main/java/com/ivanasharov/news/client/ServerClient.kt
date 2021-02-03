package com.ivanasharov.news.client

import com.ivanasharov.news.client.dto.Articles

interface ServerClient {
    suspend fun getServerNews(): List<Articles>
}