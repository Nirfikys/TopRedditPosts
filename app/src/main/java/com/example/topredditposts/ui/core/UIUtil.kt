package com.example.topredditposts.ui.core

import android.content.Context
import com.example.topredditposts.R
import java.util.*

const val SECOND = 1000
const val MINUTE = SECOND * 60
const val HOUR = MINUTE * 60

fun formatDate(date: Date, context: Context): String {
    val diff = System.currentTimeMillis() - date.time
    val unit: String
    val amount: Int
    when {
        diff > HOUR -> {
            amount = (diff / HOUR).toInt()
            unit = context.getString(R.string.hours)
        }
        diff > MINUTE -> {
            amount = (diff / MINUTE).toInt()
            unit = context.getString(R.string.minutes)
        }
        else -> {
            amount = (diff / SECOND).toInt()
            unit = context.getString(R.string.seconds)
        }
    }
    return String.format(context.getString(R.string.format_ago), amount, unit)
}