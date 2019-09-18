package com.debo.newsapp.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.debo.newsapp.repo.NewsRepository
import com.debo.newsapp.utils.AndroidDisposable
import com.debo.newsapp.utils.AppRxSchedulers
import com.debo.newsapp.view.mapper.NewsViewMapper
import com.debo.newsapp.view.model.ProjectView
import com.debo.newsapp.view.state.Resource
import com.debo.newsapp.view.state.ResourceState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val repository: NewsRepository,
    private val mapper: NewsViewMapper
) : ViewModel() {

    private var disposable = AndroidDisposable()

    private val liveData: MutableLiveData<Resource<ProjectView>> = MutableLiveData()

    val newsLiveData: LiveData<Resource<ProjectView>>
        get() = liveData

    fun fetchNewsFromDb(title: String) {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        disposable.add(
            repository.getNewsByTitleFromDb(title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        liveData.postValue(
                            Resource(
                                ResourceState.SUCCESS,
                                mapper.mapDataToView(it),
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
}