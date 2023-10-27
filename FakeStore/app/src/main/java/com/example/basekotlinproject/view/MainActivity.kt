package com.example.basekotlinproject.view

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.example.basekotlinproject.R
import com.example.basekotlinproject.base.BaseActivity
import com.example.basekotlinproject.base.BaseViewModel
import com.example.basekotlinproject.databinding.ActivityMainBinding
import com.example.basekotlinproject.utils.Notification
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private val baseViewModel by viewModels<BaseViewModel>()
    override fun initView() {
        binding.text.text = "Ahihi"
    }

    override fun initObserver() {
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun doWork() {

    }

    override fun getResLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        Log.d(MainActivity::class.java.simpleName, "onStart")
    }
    override fun onResume() {
        super.onResume()
        Log.d(MainActivity::class.java.simpleName, "onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(MainActivity::class.java.simpleName, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(MainActivity::class.java.simpleName, "onRestart")
    }

    override fun onPause() {
        super.onPause()
        Log.d(MainActivity::class.java.simpleName, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(MainActivity::class.java.simpleName, "onStop")
    }
}