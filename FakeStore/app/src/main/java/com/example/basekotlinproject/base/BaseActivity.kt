package com.example.basekotlinproject.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<B : ViewDataBinding> : AppCompatActivity() {
    protected lateinit var binding: B
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getResLayoutId())
        init()
        doWork()
    }

    private fun init() {
        initView()
        initObserver()
    }

    /**
     * Define view's actions, listeners
     */
    abstract fun initView()

    /**
     * Define data observers
     */
    abstract fun initObserver()

    /**
     * Call apis, implement some code logics....
     */
    abstract fun doWork()

    /**
     * Return resource layout id of the activity
     *
     * @return resource id
     */
    abstract fun getResLayoutId(): Int
}