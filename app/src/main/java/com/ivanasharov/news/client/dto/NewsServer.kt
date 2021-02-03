package com.ivanasharov.news.client.dto

import com.google.gson.annotations.SerializedName

data class NewsServer (

		@SerializedName("status")
		val status : String,
		@SerializedName("totalResults")
		val totalResults : Int,
		@SerializedName("articles")
		val articles : List<Articles>
)