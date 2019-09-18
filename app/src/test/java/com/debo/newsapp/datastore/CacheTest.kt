package com.debo.newsapp.datastore

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.debo.newsapp.datastore.mapper.NewsDbMapperImpl
import com.debo.newsapp.db.NewsDb
import com.debo.newsapp.test.factory.ProjectDataFactory
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import java.io.IOException

@RunWith(RobolectricTestRunner::class)
class CacheTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var newsDB: NewsDb
    private lateinit var newsDbMapper: NewsDbMapperImpl
    private lateinit var cache: Cache

    @Before
    fun initDatabase() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        newsDB = Room.inMemoryDatabaseBuilder(context, NewsDb::class.java)
            .allowMainThreadQueries()
            .build()
        newsDbMapper = NewsDbMapperImpl()
        cache = Cache(newsDB, newsDbMapper)
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        newsDB.close()
    }

    @Test
    fun insertNewsSavesData() {
        val newsList = listOf(ProjectDataFactory.makeNews())
        val testObserver = cache.saveNews(newsList).test()
        testObserver.assertComplete()
    }

    @Test
    fun getNewsReturnsData() {
        val newsList = listOf(ProjectDataFactory.makeNews())
        cache.saveNews(newsList).test()
        val testObserver = cache.getNews().test()
        testObserver.assertValue(newsList)
    }

    @Test
    fun getNewsByTitleReturnsCorrectData() {
        val news = ProjectDataFactory.makeNews()
        val title = news.title
        val newsList = listOf(news)
        cache.saveNews(newsList).test()
        val testObserver = cache.getNewsByTitle(title).test()
        testObserver.assertValue(news)
    }
}