package com.example.topredditposts.cache.post

import com.example.topredditposts.domain.ImageEntity

data  class PostCacheEntity(
    val id: String,
    val author: String,
    val thumbnail: String?,
    val numComments: Int,
    val score: Int,
    val imagesUrl: List<ImageEntity>,
    val date: Long,
    val addDate: Long
)