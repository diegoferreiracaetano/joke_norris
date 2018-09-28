package com.diegoferreiracaetano.chuckNorris.di.modules


import com.diegoferreiracaetano.chuckNorris.di.qualifier.ActivityScope
import com.diegoferreiracaetano.chuckNorris.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class Builder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [(FragmentBuildersModule::class)])
    internal abstract fun contributeMainActivity(): MainActivity
}
