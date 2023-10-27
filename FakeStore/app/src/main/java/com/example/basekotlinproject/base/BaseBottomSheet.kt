package com.example.basekotlinproject.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheet<B : ViewDataBinding> : BottomSheetDialogFragment() {
    protected lateinit var binding: B
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = onInflateView(inflater, container, savedInstanceState)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
        doWork()
    }

    /**
     * Create ViewDataBinding
     */
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