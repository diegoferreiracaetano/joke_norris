package com.diegoferreiracaetano.chuckNorris.di.modules


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diegoferreiracaetano.chuckNorris.MainViewModelFactory
import com.diegoferreiracaetano.chuckNorris.di.qualifier.ViewModelKey
import com.diegoferreiracaetano.chuckNorris.ui.detail.DetailViewModel
import com.diegoferreiracaetano.chuckNorris.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    internal abstract fun bindDetailViewModel(detailViewModel: DetailViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: MainViewModelFactory): ViewModelProvider.Factory
}