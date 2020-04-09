package com.example.topredditposts.cache

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "TopRedditPosts", null, 1) {

    companion object{
        const val IMAGE_TABLE_NAME = "images"
        const val IMAGE_ID = "id"
        const val IMAGE_URL = "url"
        const val IMAGE_POST_ID = "post_id"

        const val POSTS_TABLE_NAME = "posts"
        const val POST_ID = "id"
        const val POST_AUTHOR = "author"
        const val POST_THUMBNAIL = "thumbnail"
        const val POST_COMMENTS = "comments"
        const val POST_SCORE = "score"
        const val POST_DATE = "date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "create table $POSTS_TABLE_NAME ("
                    + "$POST_ID text primary key,"
                    + "$POST_AUTHOR text,"
                    + "$POST_THUMBNAIL text,"
                    + "$POST_COMMENTS integer,"
                    + "$POST_SCORE integer,"
                    + "$POST_DATE integer"
                    + ");"
        )

        db?.execSQL(
            "create table $IMAGE_TABLE_NAME ("
                    + "$IMAGE_ID integer primary key autoincrement,"
                    + "$IMAGE_URL text,"
                    + "$IMAGE_POST_ID text"
                    + ");"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}
}