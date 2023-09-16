package com.nvvi9.spoticloud.di

import com.nvvi9.spoticloud.repositories.YouTubeRepositoryImpl
import com.nvvi9.spoticloud.repositories.base.YouTubeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun YouTubeRepositoryImpl.binds(): YouTubeRepository
}