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
        getNextPage()
    }

    @Inject
    lateinit var postRepository: PostRepository

    val postsData = MutableLiveData<List<PostEntity>>()
    val currentPage = MutableLiveData<Int>()

    var showPostOnPage = 25
    var page = 1
        set(value) {
            if (field == value) return
            currentPage.value = value
            if (value > field)
                getNextPage()
            else
                getPrevPage()
            field = value
        }

    private fun getNextPage() {
        RX(
            {
                val afterId = postsData.value?.lastOrNull()?.id
                postRepository.getTopPosts(afterId = afterId, limit = showPostOnPage)
            },
            { handleValidResult(postsData, it) },
            { handleFailure(it) }
        )
    }

    private fun getPrevPage() {
        RX(
            {
                val beforeId = postsData.value?.firstOrNull()?.id
                postRepository.getTopPosts(beforeId = beforeId, limit = showPostOnPage)
            },
            { handleValidResult(postsData, it) },
            { handleFailure(it) }
        )
    }
}