package com.example.topredditposts.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.topredditposts.databinding.PostImageItemBinding
import com.example.topredditposts.ui.core.BaseAdapter

class PostImageAdapter : BaseAdapter<PostImageAdapter.ImageViewHolder, String>() {

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem === newItem
    }

    override fun createHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ImageViewHolder(
            PostImageItemBinding.inflate(inflater, parent, false)
        )
    }

    class ImageViewHolder(private val binding: PostImageItemBinding) :
        BaseAdapter.BaseViewHolder<String>(binding.root) {
        override fun onBind(item: String) {
            binding.imageUrl = item
        }

    }

}