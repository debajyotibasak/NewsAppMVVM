package com.debo.newsapp.repo.model

data class NewsData(
    var title: String = "",
    var description: String = "",
    var publishedAt: String = "",
    var source: String = "",
    var url: String = "",
    var urlToImage: String = ""
)