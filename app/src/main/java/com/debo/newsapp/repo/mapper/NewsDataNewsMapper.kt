package com.debo.newsapp.repo.mapper

interface NewsDataNewsMapper<D, R> {
    fun mapNewsToNewsData(type: D): R
}