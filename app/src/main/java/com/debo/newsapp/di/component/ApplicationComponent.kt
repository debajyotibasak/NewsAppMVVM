package com.debo.newsapp.di.component

import com.debo.newsapp.NewsApplication
import com.debo.newsapp.di.module.ActivityModule
import com.debo.newsapp.di.module.ApiModule
import com.debo.newsapp.di.module.AppModule
import com.debo.newsapp.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ApiModule::class,
        AppModule::class,
        ActivityModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent  {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: NewsApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(app: NewsApplication)
}