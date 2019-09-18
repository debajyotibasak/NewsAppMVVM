package com.debo.newsapp.datastore

import com.debo.newsapp.datastore.model.News
import io.reactivex.Observable

interface DataStore {
    fun getNews(): Observable<List<News>>
}