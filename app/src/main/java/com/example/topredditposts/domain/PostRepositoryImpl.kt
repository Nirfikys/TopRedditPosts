package com.example.topredditposts.domain

import com.example.topredditposts.cache.post.PostCache
import com.example.topredditposts.remote.PostRemote
import java.lang.Exception

class PostRepositoryImpl(private val remote: PostRemote, private val cache: PostCache) :
    PostRepository {
    override fun getTopPosts(afterId: String?, limit: Int): List<PostEntity> {
        val cacheTopPosts = cache.getTopPosts(afterId)
        if (cacheTopPosts.isNotEmpty())
            return cacheTopPosts
        val remoteTopPosts = remote.getTopPosts(afterId, limit)
        try {
            cache.saveTopPosts(remoteTopPosts)
        } catch (ignore: Exception) {
        }
        return remoteTopPosts
    }
}