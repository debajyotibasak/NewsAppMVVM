package com.debo.newsapp.datastore

import com.debo.newsapp.datastore.mapper.NewsDbMapperImpl
import com.debo.newsapp.datastore.model.News
import com.debo.newsapp.db.NewsDb
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class Cache @Inject constructor(
    var database: NewsDb,
    var newsDbMapper: NewsDbMapperImpl
) : DataStore {

    override fun getNews(): Observable<List<News>> {
        return database.newsDao().getNews()
            .toObservable()
            .map {
                it.map { newsEntity ->
                    newsDbMapper.mapEntityToNews(newsEntity)
                }
            }
    }

    fun getNewsByTitle(title: String): Observable<News> {
        return database.newsDao().getNewsByTitle(title)
            .map {
                newsDbMapper.mapEntityToNews(it)
            }
    }

    fun saveNews(news: List<News>): Completable {
        return Completable.defer {
            val listNews = news.map {
                newsDbMapper.mapNewsToEntity(it)
            }
            database.newsDao().insertNews(listNews)
            Completable.complete()
        }
    }

}