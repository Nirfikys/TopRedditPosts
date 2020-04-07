package com.example.topredditposts.presentation.modules

import com.example.topredditposts.domain.PostRepository
import com.example.topredditposts.domain.PostRepositoryImpl
import com.example.topredditposts.remote.PostRemote
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    fun provideRepository(remote: PostRemote): PostRepository {
        return PostRepositoryImpl(remote)
    }
}