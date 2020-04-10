package com.example.topredditposts.presentation.modules

import android.content.Context
import com.example.topredditposts.cache.post.PostCache
import com.example.topredditposts.domain.PostRepository
import com.example.topredditposts.domain.PostRepositoryImpl
import com.example.topredditposts.remote.PostRemote
import dagger.Module
import dagger.Provides
import org.intellij.lang.annotations.PrintFormat
import javax.inject.Singleton

@Module
class AppModule(val context: Context) {
    @Provides
    @Singleton
    fun provideAppContext(): Context = context

    @Singleton
    @Provides
    fun provideRepository(remote: PostRemote, cache: PostCache): PostRepository {
        return PostRepositoryImpl(remote, cache)
    }
}