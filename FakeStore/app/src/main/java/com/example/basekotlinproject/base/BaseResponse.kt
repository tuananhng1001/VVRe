package com.example.basekotlinproject.base

import com.google.gson.annotations.SerializedName

data class BaseResponse(
    @SerializedName("status")
    val status: Int = 0,

    @SerializedName("code")
    val code: Int = -1,

    @SerializedName("message")
    val errorMessage: String? = "",

    @SerializedName("data")
    val data: String? = "",
)
