package com.example.topredditposts.presentation.modules

import com.example.topredditposts.remote.PostRemoteImpl
import com.example.topredditposts.remote.PostRemote
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
    fun providePostRemoteRepository(request: Request): PostRemote {
        return PostRemoteImpl(request)
    }
}