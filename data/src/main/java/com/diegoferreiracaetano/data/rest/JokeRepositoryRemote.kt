package com.diegoferreiracaetano.data.rest

import com.diegoferreiracaetano.domain.joke.Joke
import com.diegoferreiracaetano.domain.joke.JokeRepository
import io.reactivex.Flowable
import io.reactivex.Maybe
import javax.inject.Inject

class JokeReposistoryRemote @Inject constructor(private val api: ChuckNorrisApi) : JokeRepository {

    override fun getJoke(category: String): Maybe<Joke> {
       return api.getJoke(category.decapitalize())
               .map { JokeEntityRemote.parse(it) }
    }

    override fun getCategories(): Flowable<List<String>> {
        return api.getCategories()
                .flatMap { Flowable.fromIterable(it) }
                .map{it.capitalize()}
                .toList()
                .toFlowable()
    }
}
