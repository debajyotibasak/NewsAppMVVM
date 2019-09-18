package com.debo.newsapp.datastore.mapper

import com.debo.newsapp.api.model.Article
import com.debo.newsapp.datastore.model.News
import com.debo.newsapp.test.factory.ProjectDataFactory
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NewsApiMapperImplTest {

    private val newsApiMapper = NewsApiMapperImpl()

    @Test
    fun mapFromApiTest() {
        val article = ProjectDataFactory.makeArticle()
        val data = newsApiMapper.mapApiToNews(article)
        assertEqualData(data, article)
    }

    private fun assertEqualData(news: News, article: Article) {
        assertEquals(news.title, article.title)
        assertEquals(news.description, article.description)
        assertEquals(news.publishedAt, article.publishedAt)
        assertEquals(news.source, article.source.name)
        assertEquals(news.url, article.url)
        assertEquals(news.urlToImage, article.urlToImage)
    }
}