package com.example.topredditposts.domain

import com.example.topredditposts.remote.DataX
import java.util.*

fun DataX.toEntity(): PostEntity {
    return PostEntity(
        id,
        author,
        thumbnail,
        numComments,
        score,
        preview?.images?.map { it.source.url }.orEmpty(),
        Date(created)
    )
}