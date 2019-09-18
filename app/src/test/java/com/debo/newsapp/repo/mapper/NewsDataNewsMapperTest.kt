package com.debo.newsapp.repo.mapper

import com.debo.newsapp.datastore.model.News
import com.debo.newsapp.repo.model.NewsData
import com.debo.newsapp.test.factory.ProjectDataFactory
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NewsDataNewsMapperTest {

    private val newsApiMapper = NewsDataNewsMapperImpl()

    @Test
    fun mapFromApiTest() {
        val news = ProjectDataFactory.makeNews()
        val newsData = newsApiMapper.mapNewsToNewsData(news)
        assertEqualData(news, newsData)
    }

    private fun assertEqualData(news: News, newsData: NewsData) {
        assertEquals(news.title, newsData.title)
        assertEquals(news.description, newsData.description)
        assertEquals(news.publishedAt, newsData.publishedAt)
        assertEquals(news.source, newsData.source)
        assertEquals(news.url, newsData.url)
        assertEquals(news.urlToImage, newsData.urlToImage)
    }
}