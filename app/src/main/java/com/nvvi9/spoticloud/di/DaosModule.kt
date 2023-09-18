package com.nvvi9.spoticloud.di

import com.nvvi9.spoticloud.database.SpotiCloudDatabase
import com.nvvi9.spoticloud.database.dao.VideoInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaosModule {

    @Provides
    fun provideVideoInfoDao(database: SpotiCloudDatabase): VideoInfoDao = database.videoInfoDao()
}