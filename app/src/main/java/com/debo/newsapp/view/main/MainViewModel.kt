package com.debo.newsapp.view.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.debo.newsapp.repo.NewsRepository
import com.debo.newsapp.utils.AndroidDisposable
import com.debo.newsapp.view.mapper.NewsViewMapper
import com.debo.newsapp.view.model.ProjectView
import com.debo.newsapp.view.state.Resource
import com.debo.newsapp.view.state.ResourceState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val mapper: NewsViewMapper
) : ViewModel() {

    private var disposable = AndroidDisposable()

    private val liveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()

    val newsLiveData: LiveData<Resource<List<ProjectView>>>
        get() = liveData

    fun fetchNews() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        disposable.add(
            repository.getNews()
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.io())
                .subscribeBy(
                    onNext = {
                        liveData.postValue(
                            Resource(
                                ResourceState.SUCCESS,
                                it.map { newsData ->
                                    mapper.mapDataToView(newsData)
                                },
                                null
                            )
                        )
                    },
                    onError = {
                        liveData.postValue(
                            Resource(
                                ResourceState.ERROR,
                                null,
                                it.message
                            )
                        )
                    },
                    onComplete = {
                    }
                )
        )
    }

    fun fetchNewsFromDb() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        disposable.add(
            repository.getNewsFromDb()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        liveData.postValue(
                            Resource(
                                ResourceState.SUCCESS,
                                it.map { newsData ->
                                    mapper.mapDataToView(newsData)
                                },
                                null
                            )
                        )
                    },
                    onError = {
                        liveData.postValue(
                            Resource(
                                ResourceState.ERROR,
                                null,
                                it.message
                            )
                        )
                    },
                    onComplete = {
                    }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

}