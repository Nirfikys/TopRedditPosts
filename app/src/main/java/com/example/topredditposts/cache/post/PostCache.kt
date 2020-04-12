package com.example.topredditposts.cache.post

import com.example.topredditposts.domain.PostEntity

interface PostCache {
    fun saveTopPosts(posts: List<PostEntity>)
    fun getTopPosts(afterId:String? = null, beforeId:String? = null, limit: Int = 25): List<PostEntity>
}