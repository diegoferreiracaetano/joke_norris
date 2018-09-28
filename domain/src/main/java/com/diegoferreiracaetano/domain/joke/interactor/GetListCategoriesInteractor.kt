package com.diegoferreiracaetano.domain.joke.interactor

import com.diegoferreiracaetano.domain.InteractorFlowable
import com.diegoferreiracaetano.domain.joke.JokeRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetListCategoriesInteractor @Inject
constructor(private val repository: JokeRepository) : InteractorFlowable<List<String>, GetListCategoriesInteractor.Request>() {
    override fun create(request: Request?): Flowable<List<String>> {
        return repository.getCategories()
    }
    class Request() : InteractorFlowable.Request() {

    }
}