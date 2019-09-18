package com.debo.newsapp.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.debo.newsapp.db.entity.NewsEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable

@Dao
abstract class NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertNews(project: List<NewsEntity>)

    @Query("SELECT * FROM headlines")
    abstract fun getNews(): Flowable<List<NewsEntity>>

    @Query("SELECT * FROM headlines WHERE title =:selectedTitle")
    abstract fun getNewsByTitle(selectedTitle: String): Observable<NewsEntity>
}