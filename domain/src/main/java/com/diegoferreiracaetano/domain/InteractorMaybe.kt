package com.diegoferreiracaetano.domain

import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class InteractorMaybe<T, R : InteractorMaybe.Request> {

    protected abstract fun create(request: R?): Maybe<T>

    fun execute(request: R?): Maybe<T> {
        return create(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

    }

    abstract class Request
}
