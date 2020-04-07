package com.example.topredditposts.remote

import com.example.topredditposts.domain.PostEntity
import com.example.topredditposts.domain.toEntity
import com.example.topredditposts.remote.core.Request
import com.google.gson.Gson

class PostRemoteImpl(private val request: Request) : PostRemote {

    companion object {
        const val TOP = "top.json"
    }

    override fun getTopPosts(count: Int, limit: Int): List<PostEntity> {
        val uri = "$TOP?count=$count&limit=$limit"
        val page = request.make(uri) { Gson().fromJson(it, PageRemoteEntity::class.java) }
        return page.data.children.map { it.data.toEntity() }
    }

}