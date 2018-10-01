package com.diegoferreiracaetano.chuckNorris.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegoferreiracaetano.domain.joke.Joke
import com.diegoferreiracaetano.domain.joke.interactor.GetJokeInteractor
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class DetailViewModel
@Inject constructor(private val getJokeInteractor: GetJokeInteractor) : ViewModel() {

    private val disposable = CompositeDisposable()
    val category = MutableLiveData<String>()
    val joke = MutableLiveData<Joke>()
    val error = MutableLiveData<Throwable>()
    val loading = MutableLiveData<Boolean>()
    val empty = MutableLiveData<Boolean>()

    fun retry(cat: String){
         getJoke(cat,true)
    }

    fun getJoke(cat: String, retry:Boolean = false) {

        if(cat != category.value || retry) {
            loading.postValue(true)
            category.postValue(cat)
            disposable.add(getJokeInteractor.execute(GetJokeInteractor.Request(cat))
                    .subscribeBy(
                            onSuccess = {
                                joke.postValue(it)
                                loading.postValue(false)
                                empty.postValue(false)
                            },
                            onError = {
                                loading.postValue(false)
                                error.postValue(it)
                            },
                            onComplete = {
                                empty.postValue(true)
                            }))
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}
