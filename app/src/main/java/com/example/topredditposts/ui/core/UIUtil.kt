package com.example.topredditposts.ui.core

import android.content.Context
import com.example.topredditposts.R
import java.util.*

const val SECOND = 60 * 1000
const val MINUTE = SECOND * 60
const val HOUR = MINUTE * 60

fun formatDate(date: Date, context: Context): String {
    val diff = System.currentTimeMillis() - date.time
    val howTime = when {
        diff > HOUR -> (diff / HOUR).toString() + context.getString(R.string.hours)
        diff > MINUTE -> (diff / MINUTE).toString() + context.getString(R.string.minutes)
        else -> (diff / SECOND).toString() + context.getString(R.string.seconds)
    }
    return howTime + context.getString(R.string.ago)
}