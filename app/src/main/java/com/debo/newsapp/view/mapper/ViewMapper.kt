package com.debo.newsapp.view.mapper

interface ViewMapper<D, V> {
    fun mapDataToView(type: D): V
}