package com.example.topredditposts.cache.post

import android.content.ContentValues
import com.example.topredditposts.cache.DBHelper
import com.example.topredditposts.domain.PostEntity

class PostCacheImpl(private val dbHelper: DBHelper) : PostCache {

    override fun saveTopPosts(posts: List<PostEntity>) {
        val db = dbHelper.writableDatabase
        posts.forEach {
            val cv = ContentValues()
            cv.put(DBHelper.POST_ID, it.id)
            cv.put(DBHelper.POST_AUTHOR, it.author)
            cv.put(DBHelper.POST_COMMENTS, it.numComments)
            cv.put(DBHelper.POST_THUMBNAIL, it.imageUrl)
            cv.put(DBHelper.POST_SCORE, it.score)
            cv.put(DBHelper.POST_DATE, it.date.time)
            db.insert(DBHelper.POSTS_TABLE_NAME, null, cv)
        }
        db.close()
    }

    override fun getTopPosts(afterId: String?, limit: Int): List<PostEntity> {
        val db = dbHelper.readableDatabase

        db.close()
        return emptyList()
    }
}