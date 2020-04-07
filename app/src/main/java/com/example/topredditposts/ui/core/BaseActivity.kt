package com.example.topredditposts.ui.core

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.topredditposts.R
import java.util.concurrent.Executor

abstract class BaseActivity : AppCompatActivity() {
    abstract var currentFragment: BaseFragment

    open val contentId = R.layout.activity_layout

    open val showToolbar = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupContent()
        if (showToolbar) supportActionBar?.show() else supportActionBar?.hide()
        replaceFragment(fragment = currentFragment)
    }

    open fun setupContent() {
        setContentView(contentId)
    }

    override fun onBackPressed() {
        val backStackCount = supportFragmentManager.backStackEntryCount
        (supportFragmentManager.findFragmentById(
            R.id.fragmentContainer
        ) as BaseFragment).onBackPressed()
        if (backStackCount == 0)
            super.onBackPressed()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        currentFragment.onActivityResult(requestCode, resultCode, data)
    }

    fun addFragment(savedInstanceState: Bundle? = null, fragment: BaseFragment = currentFragment) {
        currentFragment = fragment
        savedInstanceState ?: supportFragmentManager.inTransaction {
            add(R.id.fragmentContainer, fragment)
            addToBackStack(null)
        }
    }

    fun addFragmentWithAnimation(
        fragment: BaseFragment = currentFragment
    ) {
        addFragmentWithAnimation(fragment) { standartAnimation() }
    }

    fun addFragmentWithAnimation(
        fragment: BaseFragment = currentFragment,
        animation: FragmentTransaction.() -> FragmentTransaction
    ) {
        currentFragment = fragment
        supportFragmentManager.inTransaction {
            animation()
            add(R.id.fragmentContainer, fragment)
            addToBackStack(null)
        }
    }

    fun FragmentTransaction.standartAnimation(): FragmentTransaction {
        return setTransition(
            FragmentTransaction.TRANSIT_FRAGMENT_OPEN
        )
    }

    fun replaceFragment(fragment: BaseFragment) {
        this.currentFragment = fragment
        supportFragmentManager.inTransaction {
            replace(R.id.fragmentContainer, fragment)
            addToBackStack(null)
        }
    }

    fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    fun showMessage(text:String){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }


    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) =
        beginTransaction().func().commit()

    open fun close() {
        hideSoftKeyboard()
        supportFragmentManager.popBackStack()
        currentFragment =
            (supportFragmentManager.findFragmentById(R.id.fragmentContainer) as BaseFragment)
    }

    inline fun Activity?.base(block: BaseActivity.() -> Unit) {
        (this as? BaseActivity)?.let(block)
    }
}