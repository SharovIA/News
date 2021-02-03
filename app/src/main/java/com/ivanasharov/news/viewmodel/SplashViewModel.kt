package com.ivanasharov.news.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.ivanasharov.news.repositories.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel @ViewModelInject constructor(
    @Assisted private val savedStateHandle: SavedStateHandle,
    private val mNewsRepository: NewsRepository
): ViewModel() {

    private val mIsLoadingFinish = MutableLiveData<Boolean>(false)

    val isLoadingFinish: LiveData<Boolean>
        get() = mIsLoadingFinish

    init {
        viewModelScope.launch(Dispatchers.IO) {
            mIsLoadingFinish.postValue(mNewsRepository.loadNews())
        }
    }
}