package com.diegoferreiracaetano.chuckNorris.di.modules


import com.diegoferreiracaetano.chuckNorris.di.qualifier.FragmentScope
import com.diegoferreiracaetano.chuckNorris.ui.detail.DetailFragment
import com.diegoferreiracaetano.chuckNorris.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun contributeMainFragment(): MainFragment

    @FragmentScope
    @ContributesAndroidInjector
    internal abstract fun contributeDetail(): DetailFragment

}
