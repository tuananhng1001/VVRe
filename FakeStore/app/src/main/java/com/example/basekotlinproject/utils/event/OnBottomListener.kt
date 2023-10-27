package com.example.basekotlinproject.utils.event

internal interface OnBottomListener {

    fun onBottom()

    fun onTop()

    fun onScroll(firstVisible: Int, lastVisible: Int, dx: Int, dy: Int)

    fun onShow()

    fun onHide()
}