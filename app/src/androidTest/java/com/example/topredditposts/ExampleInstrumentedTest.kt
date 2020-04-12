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
        val cache = PostCacheImpl(DBHelper(appContext))
        val remote = PostRemoteImpl(Request())
        var posts = remote.getTopPosts()
        cache.saveTopPosts(posts)
        assertThat(posts, Matchers.equalTo(cache.getTopPosts()))

        val t = posts
        val afterId = posts.last().id
        posts = remote.getTopPosts(afterId = afterId)
        assertThat(0, Matchers.equalTo(cache.getTopPosts(afterId = afterId).size))
        cache.saveTopPosts(posts)
        assertThat(posts, Matchers.equalTo(cache.getTopPosts(afterId = afterId)))

        val beforeId = posts.first().id
        posts = t

        val topPosts = cache.getTopPosts(beforeId = beforeId)
        topPosts.forEachIndexed { index, postEntity ->
            assertThat(posts[index], Matchers.equalTo(postEntity))
        }
    }
}
