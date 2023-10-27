package com.example.basekotlinproject.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Category (
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("image")
    @Expose
    var image: String? = null,
)