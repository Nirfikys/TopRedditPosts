package com.example.topredditposts.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.topredditposts.databinding.PostItemBinding
import com.example.topredditposts.domain.PostEntity
import com.example.topredditposts.ui.core.BaseAdapter

class PostsAdapter : BaseAdapter<PostsAdapter.PostViewHolder, PostEntity>() {

    override fun areContentsTheSame(oldItem: PostEntity, newItem: PostEntity): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: PostEntity, newItem: PostEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun createHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PostViewHolder(PostItemBinding.inflate(inflater, parent, false))
    }

    class PostViewHolder(private val binding: PostItemBinding) :
        BaseAdapter.BaseViewHolder<PostEntity>(binding.root) {
        override fun onBind(item: PostEntity) {
            binding.post = item
            val spanCount = if (item.imagesUrl.size >= 3) 3 else item.imagesUrl.size
            binding.postImageList.layoutManager = GridLayoutManager(binding.root.context, spanCount)
            val postImageAdapter = PostImageAdapter()
            binding.postImageList.adapter = postImageAdapter
            postImageAdapter.changeAdapterData(item.imagesUrl)
        }
    }

}