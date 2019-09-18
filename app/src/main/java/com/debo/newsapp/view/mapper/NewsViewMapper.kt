package com.debo.newsapp.view.mapper

import com.debo.newsapp.repo.model.NewsData
import com.debo.newsapp.view.model.ProjectView
import javax.inject.Inject

class NewsViewMapper @Inject constructor() : ViewMapper<NewsData, ProjectView> {
    override fun mapDataToView(type: NewsData): ProjectView {
        return ProjectView(
            title = type.title,
            description = type.description,
            publishedAt = type.publishedAt,
            source = type.source,
            url = type.url,
            urlToImage = type.urlToImage
        )
    }
}