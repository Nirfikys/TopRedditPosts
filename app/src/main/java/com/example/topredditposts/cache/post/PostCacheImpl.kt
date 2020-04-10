package com.example.topredditposts.cache.post

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.topredditposts.cache.DBHelper
import com.example.topredditposts.domain.PostEntity
import java.util.*

class PostCacheImpl(private val dbHelper: DBHelper) : PostCache {

    override fun saveTopPosts(posts: List<PostEntity>) {
        val db = dbHelper.writableDatabase
        posts.forEach {
            val cv = ContentValues()
            cv.put(DBHelper.POST_ID, it.id)
            cv.put(DBHelper.POST_AUTHOR, it.author)
            cv.put(DBHelper.POST_COMMENTS, it.numComments)
            cv.put(DBHelper.POST_THUMBNAIL, it.thumbnail)
            cv.put(DBHelper.POST_SCORE, it.score)
            cv.put(DBHelper.POST_DATE, it.date.time)

            it.imagesUrl.forEach { url ->
                val imageCv = ContentValues()
                imageCv.put(DBHelper.IMAGE_POST_ID, it.id)
                imageCv.put(DBHelper.IMAGE_URL, url)
                db.insert(DBHelper.IMAGE_TABLE_NAME, null, imageCv)
            }

            db.insert(DBHelper.POSTS_TABLE_NAME, null, cv)
        }
        db.close()
    }

    override fun getTopPosts(afterId: String?, limit: Int): List<PostEntity> {
        val result = emptyList<PostEntity>().toMutableList()
        val db = dbHelper.readableDatabase
        if (afterId == null) return getAllPosts(db)
        val afterPost = getPostById(db, afterId)
        if (afterPost != null) {
            db.query(
                DBHelper.POSTS_TABLE_NAME,
                null,
                "${DBHelper.POST_SCORE} < ?",
                arrayOf(afterPost.score.toString()),
                null,
                null,
                DBHelper.POST_SCORE,
                limit.toString()
            ).use {
                if (it.count >= limit && it.moveToFirst()) {
                    do {
                        val id = it.getString(it.getColumnIndex(DBHelper.POST_ID))
                        result += makePostEntity(it, getImagesByPostId(db, id))
                    }while (it.moveToNext())
                }
            }
        }
        db.close()
        return result.reversed()
    }

    private fun getAllPosts(db:SQLiteDatabase):List<PostEntity>{
        val result = emptyList<PostEntity>().toMutableList()
        db.query(
            DBHelper.POSTS_TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        ).use {
            if (it.moveToFirst())
                do {
                    val id = it.getString(it.getColumnIndex(DBHelper.POST_ID))
                    result += makePostEntity(it, getImagesByPostId(db, id))
                }while (it.moveToNext())
        }
        return result
    }

    private fun getPostById(db: SQLiteDatabase, postId: String): PostEntity? {
        return db.query(
            DBHelper.POSTS_TABLE_NAME,
            null,
            "${DBHelper.POST_ID} = ?",
            arrayOf(postId),
            null,
            null,
            null
        ).use {
            if (it.moveToFirst())
                return@use makePostEntity(it, getImagesByPostId(db, postId))
            else
                return@use null
        }
    }

    private fun makePostEntity(cursor: Cursor, images: List<String>): PostEntity {
        return PostEntity(
            cursor.getString(cursor.getColumnIndex(DBHelper.POST_ID)),
            cursor.getString(cursor.getColumnIndex(DBHelper.POST_AUTHOR)),
            cursor.getString(cursor.getColumnIndex(DBHelper.POST_AUTHOR)),
            cursor.getInt(cursor.getColumnIndex(DBHelper.POST_COMMENTS)),
            cursor.getInt(cursor.getColumnIndex(DBHelper.POST_SCORE)),
            images,
            Date(cursor.getLong(cursor.getColumnIndex(DBHelper.POST_DATE))))
    }

    private fun getImagesByPostId(db: SQLiteDatabase, postId: String): List<String> {
        val result = emptyList<String>().toMutableList()
        db.query(
            DBHelper.IMAGE_TABLE_NAME,
            null,
            "${DBHelper.IMAGE_POST_ID} = ?",
            arrayOf(postId),
            null,
            null,
            null
        ).use {
            if (it.moveToFirst()) {
                do {
                    result += it.getString(it.getColumnIndex(DBHelper.IMAGE_URL))
                } while (it.moveToNext())
            }
        }
        return result
    }
}