package com.example.topredditposts.ui.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LifecycleOwner.observe(data: LiveData<T>, f:(T) -> Unit){
    data.observe(this, Observer(f))
}