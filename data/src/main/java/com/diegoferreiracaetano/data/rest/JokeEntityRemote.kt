package com.diegoferreiracaetano.data.rest

import com.diegoferreiracaetano.domain.joke.Joke

data class JokeEntityRemote(var id:String, var icon_url: String, var url: String,var value:String) {

    companion object {
        fun parse(entityRemote: JokeEntityRemote): Joke {
           return Joke(entityRemote.id,entityRemote.icon_url,entityRemote.url,entityRemote.value)
        }
    }
}
