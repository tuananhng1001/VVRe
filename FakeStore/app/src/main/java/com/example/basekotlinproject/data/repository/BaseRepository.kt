package com.example.basekotlinproject.data.repository

import com.example.basekotlinproject.base.BaseResponse
import com.example.basekotlinproject.data.remote.BaseService
import javax.inject.Inject

interface BaseRepository {
    suspend fun test(): BaseResponse
}

class BaseRepositoryImpl @Inject constructor(
    private val baseService: BaseService
) : BaseRepository {
    override suspend fun test(): BaseResponse {
        return baseService.test()
    }
}