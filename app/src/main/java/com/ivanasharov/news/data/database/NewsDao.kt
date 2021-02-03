package com.ivanasharov.news.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Query("SELECT * FROM NewsData")
    fun loadNews(): Flow<List<NewsData>>

    @Insert
    fun insert(listNews : List<NewsData>): List<Long?>
}