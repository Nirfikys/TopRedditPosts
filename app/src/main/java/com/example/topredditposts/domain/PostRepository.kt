package com.example.topredditposts.domain

interface PostRepository {
    fun getTopPosts(count: Int = 0, limit: Int = 25): List<PostEntity>
}