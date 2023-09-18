package com.nvvi9.spoticloud.di

import com.nvvi9.spoticloud.database.VideoInfoDataSource
import com.nvvi9.spoticloud.database.VideoInfoDataSourceImpl
import com.nvvi9.spoticloud.network.youtube.YouTubeNetworkDataSource
import com.nvvi9.spoticloud.network.youtube.YouTubeNetworkDataSourceImpl
import com.nvvi9.spoticloud.network.ytstream.YTStreamDataSource
import com.nvvi9.spoticloud.network.ytstream.YTStreamDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {

    @Binds
    fun YTStreamDataSourceImpl.bindsYTStreamDataSource(): YTStreamDataSource

    @Binds
    fun YouTubeNetworkDataSourceImpl.bindsYouTubeNetworkDataSource(): YouTubeNetworkDataSource

    @Binds
    fun VideoInfoDataSourceImpl.bindVideoInfoDataSource(): VideoInfoDataSource
}