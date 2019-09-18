package com.debo.newsapp.view.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.debo.newsapp.repo.NewsRepository
import com.debo.newsapp.test.factory.ProjectDataFactory
import com.debo.newsapp.utils.AppRxSchedulers
import com.debo.newsapp.utils.RxImmediateSchedulerTestRule
import com.debo.newsapp.utils.TestRxSchedulers
import com.debo.newsapp.view.mapper.NewsViewMapper
import com.debo.newsapp.view.model.ProjectView
import com.debo.newsapp.view.state.Resource
import com.debo.newsapp.view.state.ResourceState
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import junit.framework.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerTestRule()

    var repository = mock<NewsRepository>()
    var mapper = mock<NewsViewMapper>()
    var observer = mock<Observer<Resource<List<ProjectView>>>>()

    var mainViewModel = MainViewModel(repository, mapper)

    @Test
    fun fetchNewsTest() {
        val newsData = ProjectDataFactory.makeNewsData()
        val newsView = mapper.mapDataToView(newsData)
        val listNewsData = listOf(newsData)
        val listNewsView = listOf(newsView)

        whenever(
            repository.getNews()
        ).thenReturn(Observable.just(listNewsData))

        this.mainViewModel.fetchNews()
        this.mainViewModel.newsLiveData.observeForever(observer)

        assertNotNull(this.mainViewModel.newsLiveData.value)

        assertEquals(ResourceState.SUCCESS, this.mainViewModel.newsLiveData.value?.status)
        assertEquals(listNewsView, this.mainViewModel.newsLiveData.value?.data)
        assertEquals(null, this.mainViewModel.newsLiveData.value?.message)
    }

    @Test
    fun fetchNewsFromDbTest() {
        val newsData = ProjectDataFactory.makeNewsData()
        val newsView = mapper.mapDataToView(newsData)
        val listNewsData = listOf(newsData)
        val listNewsView = listOf(newsView)

        whenever(
            repository.getNewsFromDb()
        ).thenReturn(Observable.just(listNewsData))

        this.mainViewModel.fetchNewsFromDb()
        this.mainViewModel.newsLiveData.observeForever(observer)

        assertNotNull(this.mainViewModel.newsLiveData.value)

        assertEquals(ResourceState.SUCCESS, this.mainViewModel.newsLiveData.value?.status)
        assertEquals(listNewsView, this.mainViewModel.newsLiveData.value?.data)
        assertEquals(null, this.mainViewModel.newsLiveData.value?.message)
    }
}