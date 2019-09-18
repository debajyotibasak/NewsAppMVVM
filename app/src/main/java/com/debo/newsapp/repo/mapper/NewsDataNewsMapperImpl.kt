package com.debo.newsapp.repo.mapper

import com.debo.newsapp.datastore.model.News
import com.debo.newsapp.repo.model.NewsData
import javax.inject.Inject

class NewsDataNewsMapperImpl @Inject constructor(): NewsDataNewsMapper<News, NewsData> {
    override fun mapNewsToNewsData(type: News): NewsData {
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