package com.diegoferreiracaetano.chuckNorris

import com.diegoferreiracaetano.chuckNorris.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MainApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<MainApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}