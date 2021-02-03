package com.ivanasharov.news.di

import android.app.Application
import androidx.room.Room
import com.ivanasharov.news.client.ServerClient
import com.ivanasharov.news.client.ServerClientImpl
import com.ivanasharov.news.data.database.NewsDatabase
import com.ivanasharov.news.repositories.NewsRepository
import com.ivanasharov.news.repositories.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideServerClient(): ServerClient {
        return ServerClientImpl()
    }

    @Singleton
    @Provides
    fun providesNewsRepository(serverClient: ServerClient,
    database: NewsDatabase): NewsRepository = NewsRepositoryImpl(serverClient, database)

    @Provides
    fun provideRoom(application: Application): NewsDatabase {
        return Room.databaseBuilder(application,
            NewsDatabase::class.java, "NewsDataBase")
            .build()
    }
}