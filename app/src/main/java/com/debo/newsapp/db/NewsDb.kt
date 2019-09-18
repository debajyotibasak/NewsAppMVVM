package com.debo.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.debo.newsapp.db.dao.NewsDao
import com.debo.newsapp.db.entity.NewsEntity
import com.debo.newsapp.utils.ProjectConstants
import javax.inject.Inject

@Database(
    entities = [NewsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDb : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {
        private var INSTANCE: NewsDb? = null
        private val lock = Any()

        fun getInstance(context: Context): NewsDb {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            NewsDb::class.java,
                            ProjectConstants.DATABASE_NAME
                        ).build()
                    }
                    return INSTANCE as NewsDb
                }
            }
            return INSTANCE as NewsDb
        }
    }
}