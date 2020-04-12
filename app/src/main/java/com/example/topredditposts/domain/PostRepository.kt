package com.example.topredditposts.domain

interface PostRepository {
    fun getTopPosts(afterId:String? = null, beforeId:String? = null, limit: Int = 25): List<PostEntity>
}