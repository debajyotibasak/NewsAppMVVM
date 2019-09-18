package com.debo.newsapp.datastore

import com.debo.newsapp.api.ApiInterface
import com.debo.newsapp.datastore.mapper.NewsApiMapperImpl
import com.debo.newsapp.datastore.model.News
import com.debo.newsapp.utils.ProjectConstants.API_KEY
import com.debo.newsapp.utils.ProjectConstants.COUNTRY
import io.reactivex.Observable
import javax.inject.Inject

class Remote @Inject constructor(
    var api: ApiInterface,
    var newsApiMapper: NewsApiMapperImpl
) : DataStore {
    override fun getNews(): Observable<List<News>> {
        return api.getHeadlines(COUNTRY, API_KEY)
            .map {
                it.articles.map { response ->
                    newsApiMapper.mapApiToNews(response)
                }
            }
    }
}