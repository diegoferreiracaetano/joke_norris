package com.diegoferreiracaetano.chuckNorris.di.modules

import com.diegoferreiracaetano.chuckNorris.BuildConfig
import com.diegoferreiracaetano.data.rest.ChuckNorrisApi
import com.diegoferreiracaetano.data.rest.JokeReposistoryRemote
import com.diegoferreiracaetano.domain.joke.JokeRepository
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
class RepositoryModule{

    @Provides
    @Singleton
    internal fun providerHttpClient(): OkHttpClient {

        val httpClient = OkHttpClient().newBuilder()
                .connectTimeout(60 , TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor(interceptor)

        httpClient.addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json")

                val request = requestBuilder.build()
                return chain.proceed(request)
            }
        })


        return httpClient.build();
    }

    @Provides
    @Singleton
    internal fun providerRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    internal fun providerJokeRepository(retrofit: Retrofit): JokeRepository {
        var api = retrofit.create(ChuckNorrisApi::class.java)
        return JokeReposistoryRemote(api)
    }

}