package com.example.topredditposts.remote

import com.example.topredditposts.domain.PostEntity

interface PostRemote {
    fun getTopPosts(afterId:String? = null, beforeId:String? = null, limit: Int = 25): List<PostEntity>
}