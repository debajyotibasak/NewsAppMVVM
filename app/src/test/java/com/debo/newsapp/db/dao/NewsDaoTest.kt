package com.debo.newsapp.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.debo.newsapp.db.NewsDb
import com.debo.newsapp.test.factory.ProjectDataFactory
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class NewsDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database =
        Room.inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), NewsDb::class.java)
            .allowMainThreadQueries()
            .build()

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun getNewsReturnsData() {
        val newsList = listOf(ProjectDataFactory.makeNewsEntity())
        database.newsDao().insertNews(newsList)

        val testObserver = database.newsDao().getNews().test()
        testObserver.assertValue(newsList)
    }

    @Test
    fun getNewsByTitleReturnsData() {
        val singleEntity = ProjectDataFactory.makeNewsEntity()
        val newsList = listOf(singleEntity)
        val title = singleEntity.title

        database.newsDao().insertNews(newsList)

        val testObserver = database.newsDao().getNewsByTitle(title).test()
        testObserver.assertValue(singleEntity)
    }
}