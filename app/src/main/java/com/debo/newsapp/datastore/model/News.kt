package com.debo.newsapp.datastore.model

data class News(
    var title: String = "",
    var description: String = "",
    var publishedAt: String = "",
    var source: String = "",
    var url: String = "",
    var urlToImage: String = ""
)