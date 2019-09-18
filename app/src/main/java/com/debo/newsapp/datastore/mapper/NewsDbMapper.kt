package com.debo.newsapp.datastore.mapper

interface NewsDbMapper<E, D> {
    fun mapEntityToNews(type: E): D
    fun mapNewsToEntity(type: D): E
}