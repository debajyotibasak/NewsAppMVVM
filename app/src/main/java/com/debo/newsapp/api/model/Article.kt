package com.debo.newsapp.api.model

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("title")
    var title: String = "",
    @SerializedName("description")
    var description: String? = "",
    @SerializedName("publishedAt")
    var publishedAt: String = "",
    @SerializedName("source")
    var source: Source = Source(),
    @SerializedName("url")
    var url: String = "",
    @SerializedName("urlToImage")
    var urlToImage: String? = ""
)