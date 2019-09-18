package com.debo.newsapp.db.mapper

import com.debo.newsapp.db.entity.NewsEntity
import com.debo.newsapp.repo.model.NewsData
import com.debo.newsapp.test.factory.ProjectDataFactory
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DbDataMapperTest {

    private val mapper = DbDataMapper()

    @Test
    fun mapFromDb() {
        val entity = ProjectDataFactory.makeNewsEntity()
        val data = mapper.mapFromDb(entity)
        assertEqualData(data, entity)
    }

    private fun assertEqualData(model: NewsData, entity: NewsEntity) {
        assertEquals(model.title, entity.title)
        assertEquals(model.description, entity.description)
        assertEquals(model.publishedAt, entity.publishedAt)
        assertEquals(model.source, entity.source)
        assertEquals(model.url, entity.url)
        assertEquals(model.urlToImage, entity.urlToImage)
    }
}