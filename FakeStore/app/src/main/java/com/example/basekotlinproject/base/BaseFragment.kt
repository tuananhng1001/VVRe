package com.example.basekotlinproject.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<B : ViewDataBinding> : Fragment() {
    protected lateinit var biding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        biding = onInflateView(layoutInflater, container, savedInstanceState)
        return biding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        doWork()
    }

    private fun init() {
        initView()
        initObserver()
    }

    abstract fun onInflateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): B

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
}