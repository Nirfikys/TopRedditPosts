package com.example.topredditposts.presentation.core

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object RX {
    operator fun <T> invoke(f: () -> T, success: (T) -> Unit, error: (Throwable) -> Unit) {
        val o = Observable.create<T> {
            it.onNext(f())
        }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(success, error)
    }
}