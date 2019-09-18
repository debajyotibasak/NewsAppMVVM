package com.debo.newsapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.debo.newsapp.di.factory.ViewModelFactory
import com.debo.newsapp.view.detail.DetailViewModel
import com.debo.newsapp.view.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import wellthy.care.di.scope.ViewModelKey

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    internal abstract fun bindDetailViewModel(mainViewModel: DetailViewModel): ViewModel

}