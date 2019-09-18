package com.debo.newsapp.view.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.debo.newsapp.repo.NewsRepository
import com.debo.newsapp.test.factory.DataFactory
import com.debo.newsapp.test.factory.ProjectDataFactory
import com.debo.newsapp.utils.RxImmediateSchedulerTestRule
import com.debo.newsapp.view.mapper.NewsViewMapper
import com.debo.newsapp.view.model.ProjectView
import com.debo.newsapp.view.state.Resource
import com.debo.newsapp.view.state.ResourceState
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DetailViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerTestRule()

    var repository = mock<NewsRepository>()
    var mapper = mock<NewsViewMapper>()
    var observer = mock<Observer<Resource<ProjectView>>>()

    var detailViewModel = DetailViewModel(repository, mapper)

    @Test
    fun fetchNewsTest() {
        val newsData = ProjectDataFactory.makeNewsData()
        val newsView = mapper.mapDataToView(newsData)
        val title = DataFactory.randomUuid()

        whenever(
            repository.getNewsByTitleFromDb(title)
        ).thenReturn(Observable.just(newsData))

        this.detailViewModel.fetchNewsFromDb(title)
        this.detailViewModel.newsLiveData.observeForever(observer)

        assertNotNull(this.detailViewModel.newsLiveData.value)

        assertEquals(ResourceState.SUCCESS, this.detailViewModel.newsLiveData.value?.status)
        assertEquals(newsView, this.detailViewModel.newsLiveData.value?.data)
        assertEquals(null, this.detailViewModel.newsLiveData.value?.message)
    }
}