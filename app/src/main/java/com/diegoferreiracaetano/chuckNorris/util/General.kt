package com.diegoferreiracaetano.chuckNorris.util

fun <T> lazyThreadSafetyNone(initializer: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE, initializer)
