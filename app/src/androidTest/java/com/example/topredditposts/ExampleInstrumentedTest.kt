package com.example.topredditposts

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.topredditposts.cache.DBHelper
import com.example.topredditposts.cache.post.PostCacheImpl
import com.example.topredditposts.remote.PostRemote
import com.example.topredditposts.remote.PostRemoteImpl
import com.example.topredditposts.remote.core.Request
import org.hamcrest.Matcher
import org.hamcrest.Matchers

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    }
}
