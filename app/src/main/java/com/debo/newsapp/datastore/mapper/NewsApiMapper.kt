package com.debo.newsapp.datastore.mapper

interface NewsApiMapper<N, D> {
    fun mapApiToNews(type: N): D
}