package com.example.basekotlinproject.data.remote

import com.example.basekotlinproject.base.BaseResponse
import retrofit2.http.GET

interface BaseService {
    @GET("/")
    suspend fun test(): BaseResponse
}