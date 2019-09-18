package com.debo.newsapp.repo

import com.debo.newsapp.datastore.Cache
import com.debo.newsapp.datastore.Remote
import com.debo.newsapp.datastore.model.News
import com.debo.newsapp.repo.mapper.NewsDataNewsMapperImpl
import com.debo.newsapp.repo.model.NewsData
import com.debo.newsapp.test.factory.DataFactory
import com.debo.newsapp.test.factory.ProjectDataFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NewsRepositoryTest {

    private val mapper = mock<NewsDataNewsMapperImpl>()
    private val newsCache = mock<Cache>()
    private val remote = mock<Remote>()
    private val repository = NewsRepository(newsCache, remote, mapper)

    @Before
    fun setup() {
        stubSaveNews(Completable.complete())
    }

    @Test
    fun getNewsComplete() {
        stubGetNews(Observable.just(listOf(ProjectDataFactory.makeNews())))
        stubMapper(any(), ProjectDataFactory.makeNewsData())

        val testObserver = repository.getNews().test()
        testObserver.assertComplete()
    }

    @Test
    fun getNewsReturnsData() {
        val news = ProjectDataFactory.makeNews()
        val newsData = ProjectDataFactory.makeNewsData()

        stubGetNews(Observable.just(listOf(news)))
        stubMapper(any(), newsData)

        val testObserver = repository.getNews().test()
        testObserver.assertValues(listOf(newsData))
    }

    @Test
    fun getNewsFromDbComplete() {
        stubGetNewsFromDb(Observable.just(listOf(ProjectDataFactory.makeNews())))
        stubMapper(any(), ProjectDataFactory.makeNewsData())

        val testObserver = repository.getNewsFromDb().test()
        testObserver.assertComplete()
    }

    @Test
    fun getNewsFromDbReturnsData() {
        val news = ProjectDataFactory.makeNews()
        val newsData = ProjectDataFactory.makeNewsData()

        stubGetNewsFromDb(Observable.just(listOf(news)))
        stubMapper(any(), newsData)

        val testObserver = repository.getNewsFromDb().test()
        testObserver.assertValues(listOf(newsData))
    }

    @Test
    fun getNewsFromDbByTitleComplete() {
        stubGetNewsByTitleFromDb(Observable.just(ProjectDataFactory.makeNews()))
        stubMapper(any(), ProjectDataFactory.makeNewsData())

        val testObserver = repository.getNewsByTitleFromDb(DataFactory.randomUuid()).test()
        testObserver.assertComplete()
    }

    @Test
    fun getNewsFromDbByTitleReturnsData() {
        val news = ProjectDataFactory.makeNews()
        val newsData = ProjectDataFactory.makeNewsData()

        stubGetNewsByTitleFromDb(Observable.just(news))
        stubMapper(any(), newsData)

        val testObserver = repository.getNewsByTitleFromDb(DataFactory.randomUuid()).test()
        testObserver.assertValues(newsData)
    }

    private fun stubGetNewsFromDb(observable: Observable<List<News>>) {
        whenever(
            newsCache.getNews()
        ).thenReturn(observable)
    }

    private fun stubGetNewsByTitleFromDb(observable: Observable<News>) {
        whenever(
            newsCache.getNewsByTitle(any())
        ).thenReturn(observable)
    }

    private fun stubGetNews(observable: Observable<List<News>>) {
        whenever(
            remote.getNews()
        ).thenReturn(observable)
    }

    private fun stubMapper(news: News, newsData: NewsData) {
        whenever(
            mapper.mapNewsToNewsData(news)
        ).thenReturn(newsData)
    }

    private fun stubSaveNews(complete: Completable) {
        whenever(
            newsCache.saveNews(any())
        ).thenReturn(complete)
    }

}