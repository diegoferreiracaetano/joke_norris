package com.diegoferreiracaetano.domain.joke

import io.reactivex.Flowable
import io.reactivex.Maybe

interface JokeRepository{
    fun getJoke(category: String): Maybe<Joke>
    fun getCategories(): Flowable<List<String>>
}

