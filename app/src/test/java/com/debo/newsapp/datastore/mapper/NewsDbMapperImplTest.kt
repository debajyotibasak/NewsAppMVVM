package com.debo.newsapp.datastore.mapper

import com.debo.newsapp.datastore.model.News
import com.debo.newsapp.db.entity.NewsEntity
import com.debo.newsapp.test.factory.ProjectDataFactory
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NewsDbMapperImplTest {

    private val newsDbMapper = NewsDbMapperImpl()

    @Test
    fun mapEntityToNews() {
        val entity = ProjectDataFactory.makeNewsEntity()
        val data = newsDbMapper.mapEntityToNews(entity)
        assertEqualData(data, entity)
    }

    @Test
    fun mapNewsToEntity() {
        val data = ProjectDataFactory.makeNews()
        val entity = newsDbMapper.mapNewsToEntity(data)
        assertEqualData(data, entity)
    }

    private fun assertEqualData(news: News, entity: NewsEntity) {
        assertEquals(news.title, entity.title)
        assertEquals(news.description, entity.description)
        assertEquals(news.publishedAt, entity.publishedAt)
        assertEquals(news.source, entity.source)
        assertEquals(news.url, entity.url)
        assertEquals(news.urlToImage, entity.urlToImage)
    }
}