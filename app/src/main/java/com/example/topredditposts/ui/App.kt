package com.example.topredditposts.ui

import android.app.Application
import com.example.topredditposts.presentation.modules.AppModule
import com.example.topredditposts.presentation.modules.RemoteModule
import com.example.topredditposts.presentation.viewmodel.PostViewModel
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
@Component(modules = [RemoteModule::class, AppModule::class])
interface AppComponent {

    //fragments
    fun inject(fragment: PostsFragment)

    //viewmodel
    fun inject(viewmodel: PostViewModel)
}