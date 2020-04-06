package com.example.topredditposts.domain

import com.example.topredditposts.remote.PageRemoteEntity
import com.example.topredditposts.remote.PostRemoteRepository
import com.example.topredditposts.remote.core.Request
import com.google.gson.Gson

class PostRemoteRepositoryImpl(private val request: Request) : PostRemoteRepository {

    companion object {
        const val TOP = "top.json"
    }

    override fun getTopPosts(count: Int, limit: Int): List<PostEntity> {
        val uri = "$TOP?count=$count&limit=$limit"
        val page = request.make(TOP) { Gson().fromJson(it, PageRemoteEntity::class.java) }
        return page.data.children.map { it.data.toEntity() }
    }

}