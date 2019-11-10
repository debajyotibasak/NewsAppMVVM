package com.debo.newsapp

import android.app.Activity
import android.app.Application
import android.content.Context
import com.debo.newsapp.di.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class NewsApplication : Application(), HasActivityInjector {

    init {
        instance = this
    }

    companion object {
        private var instance: NewsApplication? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return dispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        return DaggerApplicationComponent
            .builder()
            .application(this@NewsApplication)
            .build()
            .inject(this)
    }
}