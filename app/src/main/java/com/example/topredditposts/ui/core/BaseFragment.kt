package com.example.topredditposts.ui.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    abstract val layoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(layoutId, container, false)
        setupView(view)
        return view
    }

    open fun setupView(view: View) {}

    open fun onBackPressed() {}

    inline fun base(block: BaseActivity.() -> Unit) {
        if (activity is BaseActivity)
            (activity as BaseActivity).let(block)
    }
}