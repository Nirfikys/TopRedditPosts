package com.example.topredditposts.domain

import java.util.*

class PostEntity(
    val id: String,
    val author: String,
    val thumbnail: String?,
    val numComments: Int,
    val score: Int,
    val imagesUrl: List<String>,
    val date: Date
)