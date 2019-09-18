package com.debo.newsapp.db.factory

import com.debo.newsapp.db.entity.NewsEntity
import com.debo.newsapp.test.factory.AndroidDataFactory

object AndroidProjectDataFactory {

    fun makeNewsEntity(): NewsEntity {
        return NewsEntity(
            id = AndroidDataFactory.randomInt(),
            title = AndroidDataFactory.randomUuid(),
            description = AndroidDataFactory.randomUuid(),
            publishedAt = AndroidDataFactory.randomUuid(),
            source = AndroidDataFactory.randomUuid(),
            url = AndroidDataFactory.randomUuid(),
            urlToImage = AndroidDataFactory.randomUuid()
        )
    }
}