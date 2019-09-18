package com.debo.newsapp.test.factory

import com.debo.newsapp.api.model.Article
import com.debo.newsapp.api.model.NewsResponse
import com.debo.newsapp.api.model.Source
import com.debo.newsapp.datastore.model.News
import com.debo.newsapp.db.entity.NewsEntity
import com.debo.newsapp.repo.model.NewsData

object ProjectDataFactory {

    fun makeNewsEntity(): NewsEntity {
        return NewsEntity(
            title = DataFactory.randomUuid(),
            description = DataFactory.randomUuid(),
            publishedAt = DataFactory.randomUuid(),
            source = DataFactory.randomUuid(),
            url = DataFactory.randomUuid(),
            urlToImage = DataFactory.randomUuid()
        )
    }

    fun makeNewsData(): NewsData {
        return NewsData(
            title = DataFactory.randomUuid(),
            description = DataFactory.randomUuid(),
            publishedAt = DataFactory.randomUuid(),
            source = DataFactory.randomUuid(),
            url = DataFactory.randomUuid(),
            urlToImage = DataFactory.randomUuid()
        )
    }

    fun makeSource(): Source {
        return Source(
            name = DataFactory.randomUuid()
        )
    }

    fun makeArticle(): Article {
        return Article(
            title = DataFactory.randomUuid(),
            description = DataFactory.randomUuid(),
            publishedAt = DataFactory.randomUuid(),
            source = makeSource(),
            url = DataFactory.randomUuid(),
            urlToImage = DataFactory.randomUuid()
        )
    }

    fun makeNewsResponse(): NewsResponse {
        return NewsResponse(
            status = DataFactory.randomUuid(),
            articles = listOf(makeArticle())
        )
    }

    fun makeNews(): News {
        return News(
            title = DataFactory.randomUuid(),
            description = DataFactory.randomUuid(),
            publishedAt = DataFactory.randomUuid(),
            source = DataFactory.randomUuid(),
            url = DataFactory.randomUuid(),
            urlToImage = DataFactory.randomUuid()
        )
    }
}