package com.diegoferreiracaetano.chuckNorris.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.domain.joke.interactor.GetListCategoriesInteractor
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject


class MainViewModel
@Inject constructor(private val getListCategoriesInteractor: GetListCategoriesInteractor) : ViewModel() {

    private val disposable = CompositeDisposable()
    val categories = MutableLiveData<List<String>>()
    val error = MutableLiveData<Throwable>()
    val loading = MutableLiveData<Boolean>()
    val empty = MutableLiveData<Boolean>()

    init {
        getCategories()
    }

    fun getCategories() {

        loading.postValue(true)

        disposable.add(getListCategoriesInteractor.execute(GetListCategoriesInteractor.Request())
                .subscribeBy (
                        onNext = {
                            categories.postValue(it)
                            loading.postValue(false)
                            empty.postValue(it.isEmpty())
                        },
                        onError =  {
                            loading.postValue(false)
                            error.postValue(it) },
                        onComplete = { }))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
