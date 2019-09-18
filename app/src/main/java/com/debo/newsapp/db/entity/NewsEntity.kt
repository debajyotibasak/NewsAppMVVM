package com.debo.newsapp.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.debo.newsapp.utils.ProjectConstants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class NewsEntity(
    @PrimaryKey
    var title: String = "",
    var description: String = "",
    var publishedAt: String = "",
    var source: String = "",
    var url: String = "",
    var urlToImage: String = ""
)