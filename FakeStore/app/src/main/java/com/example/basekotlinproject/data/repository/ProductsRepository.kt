package com.example.basekotlinproject.data.repository

import com.example.basekotlinproject.base.BaseResponse
import com.example.basekotlinproject.data.remote.ProductApis
import com.example.basekotlinproject.model.Product
import javax.inject.Inject

interface ProductsRepository {
    suspend fun getProducts(): BaseResponse<Product>
}

class ProductsRepositoryImpl @Inject constructor(
    private val products: ProductApis
) : ProductsRepository {
    override suspend fun getProducts(): BaseResponse<Product> {
        return products.getProducts()
    }
}