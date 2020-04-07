package com.example.topredditposts.ui.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topredditposts.R
import com.example.topredditposts.databinding.PostListLayoutBinding
import com.example.topredditposts.databinding.PostListLayoutBindingImpl
import com.example.topredditposts.domain.PostRemoteRepositoryImpl
import com.example.topredditposts.remote.core.Request
import com.example.topredditposts.ui.core.BaseFragment

class PostsFragment : BaseFragment() {
    override val layoutId: Int = R.layout.post_list_layout
    private val adapter = PostsAdapter()
    private lateinit var binding: PostListLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PostListLayoutBinding.inflate(inflater, container, false)
        binding.root.setOnClickListener { }
        setupView(binding.root)
        return binding.root
    }

    override fun setupView(view: View) {
        super.setupView(view)
        binding.topPostRecycler.adapter = adapter
        binding.topPostRecycler.layoutManager = LinearLayoutManager(context)
        Thread {
            val newItems = PostRemoteRepositoryImpl(Request()).getTopPosts()
            Handler(Looper.getMainLooper()).post { adapter.changeAdapterData(newItems) }
        }.start()
    }
}