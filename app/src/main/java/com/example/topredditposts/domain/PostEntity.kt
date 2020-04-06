package com.example.topredditposts.domain

import java.util.*

class PostEntity(
    val id: String,
    val author: String,
    val imageUrl: String?,
    val numComments: Int,
    val score: Int,
    val date: Date
)