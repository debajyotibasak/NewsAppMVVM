package com.debo.newsapp.datastore.mapper

import com.debo.newsapp.api.model.Article
import com.debo.newsapp.datastore.model.News
import javax.inject.Inject

class NewsApiMapperImpl @Inject constructor() : NewsApiMapper<Article, News> {
    override fun mapApiToNews(type: Article): News {
        return News(
            title = type.title,
            description = type.description ?: "",
            publishedAt = type.publishedAt,
            source = type.source.name,
            url = type.url,
            urlToImage = type.urlToImage ?: ""
        )
    }
}