package com.example.topredditposts.presentation.modules

import android.content.Context
import com.example.topredditposts.cache.DBHelper
import com.example.topredditposts.cache.post.PostCache
import com.example.topredditposts.cache.post.PostCacheImpl
import dagger.Module
import dagger.Provides

@Module
class CacheModule {
    @Provides
    fun providePostCache(dbHelper: DBHelper):PostCache{
        return PostCacheImpl(dbHelper)
    }

    @Provides
    fun provideDBHelper(context: Context):DBHelper{
        return DBHelper(context)
    }
}