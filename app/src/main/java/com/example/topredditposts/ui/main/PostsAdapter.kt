package com.example.topredditposts.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.topredditposts.databinding.PostItemBinding
import com.example.topredditposts.domain.PostEntity
import com.example.topredditposts.ui.core.BaseAdapter

class PostsAdapter : BaseAdapter<PostsAdapter.PostViewHolder, PostEntity>() {

    var imageOnClick:OnClick<String>? = null

    override fun areContentsTheSame(oldItem: PostEntity, newItem: PostEntity): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: PostEntity, newItem: PostEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun createHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PostViewHolder(PostItemBinding.inflate(inflater, parent, false), imageOnClick)
    }

    class PostViewHolder(private val binding: PostItemBinding, private val imageOnClick: OnClick<String>?) :
        BaseAdapter.BaseViewHolder<PostEntity>(binding.root) {
        override fun onBind(item: PostEntity) {
            binding.post = item
            if (item.imagesUrl.isNotEmpty()){
                val spanCount = if (item.imagesUrl.size >= 3) 3 else item.imagesUrl.size
                binding.postImageList.layoutManager = GridLayoutManager(binding.root.context, spanCount)
                val postImageAdapter = PostImageAdapter()
                postImageAdapter.onClick = imageOnClick
                binding.postImageList.adapter = postImageAdapter
                postImageAdapter.changeAdapterData(item.imagesUrl)
            }
        }
    }

}