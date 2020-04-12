package com.example.topredditposts.cache.post

import java.util.*

data  class PostCacheEntity(
    val id: String,
    val author: String,
    val thumbnail: String?,
    val numComments: Int,
    val score: Int,
    val imagesUrl: List<String>,
    val date: Long,
    val addDate: Long
)