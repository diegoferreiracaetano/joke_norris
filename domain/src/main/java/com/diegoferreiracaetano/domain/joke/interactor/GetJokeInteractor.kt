package com.diegoferreiracaetano.domain.joke.interactor

import com.diegoferreiracaetano.domain.InteractorMaybe
import com.diegoferreiracaetano.domain.joke.Joke
import com.diegoferreiracaetano.domain.joke.JokeRepository
import io.reactivex.Maybe
import javax.inject.Inject

class GetJokeInteractor @Inject
constructor(private val repository: JokeRepository) : InteractorMaybe<Joke, GetJokeInteractor.Request>() {

    override fun create(request: Request?): Maybe<Joke> {
        return repository.getJoke(request!!.category)
    }

    data class Request(val category: String) : InteractorMaybe.Request()
}
