package com.example.topredditposts.remote.core

import java.io.BufferedInputStream
import java.io.InputStream
import java.io.Reader
import java.net.HttpURLConnection
import java.net.URL

class Request {
    companion object {
        const val BASE_URL = "https://www.reddit.com/"

        const val USER_AGENT =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.149 Safari/537.36 OPR/67.0.3575.115"
    }

    fun <R> make(uri: String, transform: (Reader) -> R): R {
        val url = URL(BASE_URL + uri)
        val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
        urlConnection.setRequestProperty("user-agent", USER_AGENT)
        try {
            return transform(urlConnection.inputStream.bufferedReader())
        } finally {
            urlConnection.disconnect()
        }
    }
}