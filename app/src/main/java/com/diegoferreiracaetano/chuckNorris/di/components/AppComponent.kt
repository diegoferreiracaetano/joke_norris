package com.diegoferreiracaetano.chuckNorris.di.components


import com.diegoferreiracaetano.chuckNorris.MainApplication
import com.diegoferreiracaetano.chuckNorris.di.modules.AppModule
import com.diegoferreiracaetano.chuckNorris.di.modules.Builder
import com.diegoferreiracaetano.chuckNorris.di.modules.RepositoryModule
import com.diegoferreiracaetano.chuckNorris.di.modules.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
        modules = [
            (AndroidSupportInjectionModule::class),
            (Builder::class),
            (AppModule::class),
            (RepositoryModule::class),
            (ViewModelModule::class)]
)
interface AppComponent : AndroidInjector<MainApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MainApplication>()

}
