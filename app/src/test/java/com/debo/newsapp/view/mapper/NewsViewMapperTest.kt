package com.debo.newsapp.view.mapper

import com.debo.newsapp.repo.model.NewsData
import com.debo.newsapp.test.factory.ProjectDataFactory
import com.debo.newsapp.view.mapper.NewsViewMapper
import com.debo.newsapp.view.model.ProjectView
import junit.framework.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NewsViewMapperTest {

    private val newsViewMapper = NewsViewMapper()

    @Test
    fun mapToProjectViewMapper() {
        val newsData = ProjectDataFactory.makeNewsData()
        val projectView = newsViewMapper.mapDataToView(newsData)
        assertEqualData(projectView, newsData)
    }

    private fun assertEqualData(projectView: ProjectView, newsData: NewsData) {
        Assert.assertEquals(projectView.title, newsData.title)
        Assert.assertEquals(projectView.description, newsData.description)
        Assert.assertEquals(projectView.publishedAt, newsData.publishedAt)
        Assert.assertEquals(projectView.source, newsData.source)
        Assert.assertEquals(projectView.url, newsData.url)
        Assert.assertEquals(projectView.urlToImage, newsData.urlToImage)
    }
}