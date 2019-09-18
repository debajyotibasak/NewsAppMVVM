package com.debo.newsapp.datastore

import com.debo.newsapp.api.ApiInterface
import com.debo.newsapp.api.model.Article
import com.debo.newsapp.api.model.NewsResponse
import com.debo.newsapp.datastore.mapper.NewsApiMapperImpl
import com.debo.newsapp.datastore.model.News
import com.debo.newsapp.test.factory.ProjectDataFactory
import com.debo.newsapp.utils.ProjectConstants
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RemoteTest {

    private val service = mock<ApiInterface>()
    private val newsApiMapper = mock<NewsApiMapperImpl>()
    private val remote = Remote(service, newsApiMapper)

    @Test
    fun getHeadlinesApiCallComplete() {
        stubGetHeadlinesApiCall(
            Observable.just(ProjectDataFactory.makeNewsResponse())
        )
        val testObserver = remote.getNews().test()
        testObserver.assertComplete()
    }

    @Test
    fun getHeadlinesApiCallsServer() {
        stubGetHeadlinesApiCall(
            Observable.just(ProjectDataFactory.makeNewsResponse())
        )
        remote.getNews().test()
        verify(service).getHeadlines(any(), any())
    }

    @Test
    fun getHeadlinesApiCallsServerWithCorrectParameters() {
        stubGetHeadlinesApiCall(
            Observable.just(ProjectDataFactory.makeNewsResponse())
        )
        remote.getNews().test()
        verify(service).getHeadlines(ProjectConstants.COUNTRY, ProjectConstants.API_KEY)
    }

    @Test
    fun getHeadlinesReturnsData() {
        val response = ProjectDataFactory.makeNewsResponse()
        stubGetHeadlinesApiCall(
            Observable.just(response)
        )
        val news = mutableListOf<News>()
        response.articles.forEach {
            val singleNews = ProjectDataFactory.makeNews()
            news.add(singleNews)
            stubMapApiToNews(it, singleNews)
        }
        val testObserver = remote.getNews().test()
        testObserver.assertValues(news)
    }

    private fun stubGetHeadlinesApiCall(observable: Observable<NewsResponse>) {
        whenever(
            service.getHeadlines(any(), any())
        ).thenReturn(observable)
    }

    private fun stubMapApiToNews(article: Article, news: News) {
        whenever(
            newsApiMapper.mapApiToNews(article)
        ).thenReturn(news)
    }
}