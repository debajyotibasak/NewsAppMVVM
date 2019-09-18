package com.debo.newsapp.api

import com.debo.newsapp.api.model.NewsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("top-headlines")
    fun getHeadlines(
        @Query("country") sources: String,
        @Query("apiKey") apiKey: String
    ): Observable<NewsResponse>
}