package com.debo.newsapp.di.module

import android.content.Context
import com.debo.newsapp.NewsApplication
import com.debo.newsapp.db.NewsDb
import com.debo.newsapp.db.dao.NewsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun providesDataBase(application: NewsApplication): NewsDb {
            return NewsDb.getInstance(application)
        }
    }

//    @Singleton
//    @Provides
//    fun provideUserDao(db: NewsDb): NewsDao {
//        return db.newsDao()
//    }

    @Singleton
    @Provides
    fun provideContext(application: NewsApplication): Context {
        return application.applicationContext
    }
}