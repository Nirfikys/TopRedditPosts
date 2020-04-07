package com.example.topredditposts.ui

import android.app.Application
import com.example.topredditposts.presentation.RemoteModule
import com.example.topredditposts.ui.main.PostsFragment
import dagger.Component
import javax.inject.Singleton

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().build()
    }
}

@Singleton
@Component(modules = [RemoteModule::class])
interface AppComponent {

    //fragments
    fun inject(fragment: PostsFragment)
}