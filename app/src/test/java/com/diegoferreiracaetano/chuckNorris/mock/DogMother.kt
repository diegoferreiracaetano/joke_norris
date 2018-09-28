package com.diegoferreiracaetano.chuckNorris.mock


import com.diegoferreiracaetano.domain.joke.Joke

object ChuckNorrisMother {

    val fakeJoke = Joke("id","http://www.myphoto.com/1.jpg","http://www.url.com.br/","Teste ")

    val fakeCategories = listOf<String>("category1","category2")
}
