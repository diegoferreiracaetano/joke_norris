package com.diegoferreiracaetano.data.rest

import io.reactivex.Flowable
import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisApi{

    @GET("categories")
    fun getCategories(): Flowable<List<String>>

    @GET("random")
    fun getJoke(@Query("category") category: String): Maybe<JokeEntityRemote>
}
