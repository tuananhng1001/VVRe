package com.example.basekotlinproject.data.remote

import com.example.basekotlinproject.base.BaseResponse
import com.example.basekotlinproject.model.Product
import retrofit2.http.GET

interface ProductApis {
    @GET("/api/v1/products")
    suspend fun getProducts(): BaseResponse<Product>
}