package com.example.basekotlinproject.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class Product (
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("price")
    @Expose
    var price: Int? = null,

    @SerializedName("description")
    @Expose
    var description: String? = null,

    @SerializedName("category")
    @Expose
    var category: Category? = null,

    @SerializedName("images")
    @Expose
    var images: List<String>? = null,
)