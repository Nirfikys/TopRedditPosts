package com.example.topredditposts.presentation

import com.example.topredditposts.domain.PostRemoteRepositoryImpl
import com.example.topredditposts.remote.PostRemoteRepository
import com.example.topredditposts.remote.core.Request
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RemoteModule {
    @Singleton
    @Provides
    fun provideRequest(): Request {
        return Request()
    }

    @Singleton
    @Provides
    fun providePostRemoteRepository(request: Request): PostRemoteRepository {
        return PostRemoteRepositoryImpl(request)
    }
}