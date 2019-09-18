package com.debo.newsapp.db.mapper

import com.debo.newsapp.db.entity.NewsEntity
import com.debo.newsapp.repo.model.NewsData
import javax.inject.Inject

class DbDataMapper @Inject constructor(): DbMapper<NewsEntity, NewsData> {

    override fun mapFromDb(type: NewsEntity): NewsData {
        return NewsData(
            title = type.title,
            description = type.description,
            publishedAt = type.publishedAt,
            source = type.source,
            url = type.url,
            urlToImage = type.urlToImage
        )
    }
}