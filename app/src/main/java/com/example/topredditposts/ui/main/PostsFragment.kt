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
import com.example.topredditposts.ui.core.BaseAdapter
import com.example.topredditposts.ui.core.BaseFragment
import com.example.topredditposts.ui.core.observe

class PostsFragment : BaseFragment() {

    companion object {
        const val PAGE_KEY = "page"
    }

    override val layoutId: Int = R.layout.post_list_layout
    private val adapter = PostsAdapter()
    private lateinit var binding: PostListLayoutBinding
    private lateinit var postModel: PostViewModel
    private var page = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postModel = ViewModelProvider(this).get(PostViewModel::class.java)
        if (savedInstanceState != null && savedInstanceState.containsKey(PAGE_KEY)) {
            page = savedInstanceState.getInt(PAGE_KEY, 1)
            if (page != postModel.page) postModel.page = page
        } else {
            page = postModel.page
        }
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
        binding.currentPage = page
        binding.isNextPageExists = true
        binding.topPostRecycler.adapter = adapter
        adapter.imageOnClick = object : BaseAdapter.OnClick<String>{
            override fun onClick(item: String?, view: View) {
                base {
                    if (item != null)
                        addFragment(fragment = ImageFragment(item))
                }
            }

            override fun onLongClick(item: String?, view: View) { }

        }
        binding.topPostRecycler.layoutManager = LinearLayoutManager(context)
        binding.pageIndicatorNext.setOnClickListener { changePage(postModel.page + 1) }


        observe(postModel.postsData) { adapter.changeAdapterData(it) }
        observe(postModel.currentPage) { binding.currentPage = it;binding.invalidateAll() }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(PAGE_KEY, page)
    }

    private fun changePage(newPage: Int) {
        page = newPage
        postModel.page = page
    }
}