package com.debo.newsapp.repo

import com.debo.newsapp.datastore.Cache
import com.debo.newsapp.datastore.Remote
import com.debo.newsapp.repo.mapper.NewsDataNewsMapperImpl
import com.debo.newsapp.repo.model.NewsData
import io.reactivex.Observable
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsCache: Cache,
    private val newsRemote: Remote,
    private val mapper: NewsDataNewsMapperImpl
) {
    fun getNews(): Observable<List<NewsData>> {
        return newsRemote.getNews()
            .flatMap {
                newsCache.saveNews(it)
                    .andThen(
                        Observable.just(it)
                    )
            }.map {
                it.map { news ->
                    mapper.mapNewsToNewsData(news)
                }
            }
    }

    fun getNewsByTitleFromDb(title: String): Observable<NewsData> {
        return newsCache.getNewsByTitle(title)
            .map { news ->
                mapper.mapNewsToNewsData(news)
            }
    }

    fun getNewsFromDb(): Observable<List<NewsData>> {
        return newsCache.getNews()
            .map {
                it.map { news ->
                    mapper.mapNewsToNewsData(news)
                }
            }
    }
}