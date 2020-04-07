package com.example.topredditposts.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.topredditposts.domain.PostEntity
import com.example.topredditposts.domain.PostRepository
import com.example.topredditposts.presentation.core.RX
import com.example.topredditposts.ui.App
import javax.inject.Inject

class PostViewModel : BaseViewModel() {

    init {
        App.appComponent.inject(this)
    }

    @Inject
    lateinit var postRepository: PostRepository

    val postsData = MutableLiveData<List<PostEntity>>()

    fun getTopPosts(count: Int, limit: Int) {
        RX(
            { postRepository.getTopPosts() },
            { handleValidResult(postsData, it) },
            { handleFailure(it) }
        )
    }
}