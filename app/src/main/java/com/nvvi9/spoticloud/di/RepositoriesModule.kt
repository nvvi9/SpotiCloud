package com.nvvi9.spoticloud.di

import com.nvvi9.spoticloud.repositories.VideoDataRepositoryImpl
import com.nvvi9.spoticloud.repositories.VideoInfoWithStreamsAndThumbnailsRepositoryImpl
import com.nvvi9.spoticloud.repositories.YouTubeRepositoryImpl
import com.nvvi9.spoticloud.repositories.base.VideoDataRepository
import com.nvvi9.spoticloud.repositories.base.VideoInfoWithStreamsAndThumbnailsRepository
import com.nvvi9.spoticloud.repositories.base.YouTubeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun YouTubeRepositoryImpl.bindsYouTubeRepository(): YouTubeRepository

    @Binds
    fun VideoDataRepositoryImpl.bindsVideoDataRepository(): VideoDataRepository

    @Binds
    fun VideoInfoWithStreamsAndThumbnailsRepositoryImpl.bindsVideoInfoWithStreamsAndThumbnailsRepository(): VideoInfoWithStreamsAndThumbnailsRepository
}