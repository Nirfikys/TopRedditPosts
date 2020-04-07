package com.example.topredditposts.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.topredditposts.R
import com.example.topredditposts.databinding.PostListLayoutBinding
import com.example.topredditposts.presentation.viewmodel.PostViewModel
import com.example.topredditposts.ui.core.BaseFragment
import com.example.topredditposts.ui.core.observe

class PostsFragment : BaseFragment() {
    override val layoutId: Int = R.layout.post_list_layout
    private val adapter = PostsAdapter()
    private lateinit var binding: PostListLayoutBinding
    private lateinit var postModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postModel = ViewModelProvider(this).get(PostViewModel::class.java)
    }

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
        binding.currentPage = postModel.page
        binding.isNextPageExists = true
        binding.topPostRecycler.adapter = adapter
        binding.topPostRecycler.layoutManager = LinearLayoutManager(context)
        binding.pageIndicatorNext.setOnClickListener { postModel.page = postModel.page + 1 }

        observe(postModel.postsData) { adapter.changeAdapterData(it) }
        observe(postModel.currentPage) {binding.currentPage = it;binding.invalidateAll()}

    }
}