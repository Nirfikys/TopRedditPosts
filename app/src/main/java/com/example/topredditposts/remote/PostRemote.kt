package com.example.topredditposts.remote

import com.example.topredditposts.domain.PostEntity

interface PostRemote {
    fun getTopPosts(count: Int = 0, limit: Int = 25): List<PostEntity>
}