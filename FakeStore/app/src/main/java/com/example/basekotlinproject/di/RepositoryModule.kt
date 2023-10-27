package com.example.basekotlinproject.di

import com.example.basekotlinproject.data.repository.BaseRepository
import com.example.basekotlinproject.data.repository.BaseRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindBaseRepository(baseRepositoryImpl: BaseRepositoryImpl): BaseRepository
}