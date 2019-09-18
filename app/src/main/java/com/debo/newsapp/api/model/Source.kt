package com.debo.newsapp.api.model

import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("name")
    var name: String = ""
)