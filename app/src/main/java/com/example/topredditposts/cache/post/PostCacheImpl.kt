package com.example.topredditposts.cache.post

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.topredditposts.cache.DBHelper
import com.example.topredditposts.domain.ImageEntity
import com.example.topredditposts.domain.PostEntity
import com.example.topredditposts.domain.toEntity
import java.lang.Exception

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
            cv.put(DBHelper.POST_ADD_DATE, System.currentTimeMillis())

            it.images.forEach { image ->
                val imageCv = ContentValues()
                imageCv.put(DBHelper.IMAGE_ID, image.id)
                imageCv.put(DBHelper.IMAGE_POST_ID, it.id)
                imageCv.put(DBHelper.IMAGE_URL, image.url)
                db.insert(DBHelper.IMAGE_TABLE_NAME, null, imageCv)
            }

            db.insert(DBHelper.POSTS_TABLE_NAME, null, cv)
        }
        db.close()
    }

    override fun getTopPosts(afterId: String?, beforeId: String?, limit: Int): List<PostEntity> {
        val result = emptyList<PostEntity>().toMutableList()
        val db = dbHelper.readableDatabase
        if (afterId == null && beforeId == null) return getAllPosts(db, limit)
        val post: PostCacheEntity?
        val selection: String
        val orderBy: String
        when {
            afterId != null -> {
                post = getPostById(db, afterId)
                selection = "${DBHelper.POST_ADD_DATE} > ?"
                orderBy = DBHelper.POST_ADD_DATE
            }
            beforeId != null -> {
                post = getPostById(db, beforeId)
                selection = "${DBHelper.POST_ADD_DATE} < ?"
                orderBy = "${DBHelper.POST_ADD_DATE} DESC"
            }
            else -> {
                throw Exception("unreachable")
            }
        }
        if (post != null) {
            db.query(
                DBHelper.POSTS_TABLE_NAME,
                null,
                selection,
                arrayOf(post.addDate.toString()),
                null,
                null,
                orderBy,
                limit.toString()
            ).use {
                if (it.count >= limit && it.moveToFirst()) {
                    do {
                        val id = it.getString(it.getColumnIndex(DBHelper.POST_ID))
                        result += makePostEntity(it, getImagesByPostId(db, id)).toEntity()
                    } while (it.moveToNext())
                }
            }
        }
        db.close()
        return if (afterId != null) result else result.reversed()
    }

    private fun getAllPosts(db: SQLiteDatabase, limit: Int): List<PostEntity> {
        val result = emptyList<PostEntity>().toMutableList()
        db.query(
            DBHelper.POSTS_TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null,
            limit.toString()
        ).use {
            if (it.moveToFirst())
                do {
                    val id = it.getString(it.getColumnIndex(DBHelper.POST_ID))
                    result += makePostEntity(it, getImagesByPostId(db, id)).toEntity()
                } while (it.moveToNext())
        }
        return result
    }

    private fun getPostById(db: SQLiteDatabase, postId: String): PostCacheEntity? {
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

    private fun makePostEntity(cursor: Cursor, images: List<ImageEntity>): PostCacheEntity {
        return PostCacheEntity(
            cursor.getString(cursor.getColumnIndex(DBHelper.POST_ID)),
            cursor.getString(cursor.getColumnIndex(DBHelper.POST_AUTHOR)),
            cursor.getString(cursor.getColumnIndex(DBHelper.POST_THUMBNAIL)),
            cursor.getInt(cursor.getColumnIndex(DBHelper.POST_COMMENTS)),
            cursor.getInt(cursor.getColumnIndex(DBHelper.POST_SCORE)),
            images,
            cursor.getLong(cursor.getColumnIndex(DBHelper.POST_DATE)),
            cursor.getLong(cursor.getColumnIndex(DBHelper.POST_ADD_DATE))
        )
    }

    private fun getImagesByPostId(db: SQLiteDatabase, postId: String): List<ImageEntity> {
        val result = emptyList<ImageEntity>().toMutableList()
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
                    val image = ImageEntity(
                        it.getString(it.getColumnIndex(DBHelper.IMAGE_ID)),
                        it.getString(it.getColumnIndex(DBHelper.IMAGE_URL))
                    )
                    result += image
                } while (it.moveToNext())
            }
        }
        return result
    }
}