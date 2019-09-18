package com.debo.newsapp.datastore.mapper

import com.debo.newsapp.datastore.model.News
import com.debo.newsapp.db.entity.NewsEntity
import javax.inject.Inject

class NewsDbMapperImpl @Inject constructor(): NewsDbMapper<NewsEntity, News> {
    override fun mapEntityToNews(type: NewsEntity): News {
        return News(
            title = type.title,
            description = type.description,
            publishedAt = type.publishedAt,
            source = type.source,
            url = type.url,
            urlToImage = type.urlToImage
        )
    }

    override fun mapNewsToEntity(type: News): NewsEntity {
        return NewsEntity(
            title = type.title,
            description = type.description,
            publishedAt = type.publishedAt,
            source = type.source,
            url = type.url,
            urlToImage = type.urlToImage
        )
    }
}