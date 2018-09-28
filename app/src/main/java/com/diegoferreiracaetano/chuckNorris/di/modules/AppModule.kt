package com.diegoferreiracaetano.chuckNorris.di.modules


import android.content.Context
import com.diegoferreiracaetano.chuckNorris.MainApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: MainApplication): Context {
        return application.applicationContext
    }

}

