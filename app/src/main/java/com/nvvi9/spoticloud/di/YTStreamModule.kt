package com.nvvi9.spoticloud.di

import android.content.Context
import com.nvvi9.ytstream.YTStream
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object YTStreamModule {

    @Provides
    @Singleton
    fun provideYTStream(@ApplicationContext context: Context): YTStream =
        YTStream(context)
}