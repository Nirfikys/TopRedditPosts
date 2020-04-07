package com.example.topredditposts.remote

import com.example.topredditposts.domain.PostEntity
import com.example.topredditposts.domain.toEntity
import com.example.topredditposts.remote.core.Request
import com.google.gson.Gson
import java.lang.StringBuilder

class PostRemoteImpl(private val request: Request) : PostRemote {

    companion object {
        const val TOP = "top.json"
    }

    override fun getTopPosts(afterId: String?, limit: Int): List<PostEntity> {
        val sb = StringBuilder()
        sb.append(TOP)
        if (afterId != null || limit != 0){
            sb.append("?")
            if (afterId != null)
                sb.append("after=t3_$afterId&")
            if (limit != 0)
                sb.append("limit=$limit&")
            if (sb.elementAt(sb.lastIndex) == '&')
                sb.deleteCharAt(sb.lastIndex)
        }
        val uri = sb.toString()
        val page = request.make(uri) { Gson().fromJson(it, PageRemoteEntity::class.java) }
        return page.data.children.map { it.data.toEntity() }
    }

}