package com.ivanasharov.news.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.ivanasharov.news.models.NewsViewModel
import com.ivanasharov.news.repositories.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ListNewsViewModel  @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val mNewsRepository: NewsRepository
): ViewModel() {

    private val mNewsList = MutableLiveData<List<NewsViewModel>>()

    val newsList: LiveData<List<NewsViewModel>>
        get() = mNewsList

    init {getData()}

    private fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            mNewsRepository.getAllNews().map { list ->
                list.map {
                    NewsViewModel(it)
                }
            }.map{
                it.toMutableList().sortByDescending{
                    it.publishedAt
                }
                it
            }.collect{
                mNewsList.postValue(it)
            }
        }
    }
}