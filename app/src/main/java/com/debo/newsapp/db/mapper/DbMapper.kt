package com.debo.newsapp.db.mapper

interface DbMapper<E, D> {
    fun mapFromDb(type: E): D
}