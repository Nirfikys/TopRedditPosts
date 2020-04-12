package com.example.topredditposts.domain

import java.util.*

data  class PostEntity(
    val id: String,
    val author: String,
    val thumbnail: String?,
    val numComments: Int,
    val score: Int,
    val images: List<ImageEntity>,
    val date: Date
)