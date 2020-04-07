package com.example.topredditposts.domain

import com.example.topredditposts.remote.PostRemote

class PostRepositoryImpl(private val remote: PostRemote) : PostRepository {
    override fun getTopPosts(afterId: String?, limit: Int): List<PostEntity> {
        return remote.getTopPosts(afterId, limit)
    }
}