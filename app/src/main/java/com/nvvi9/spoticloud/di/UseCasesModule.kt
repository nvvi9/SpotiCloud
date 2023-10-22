package com.nvvi9.spoticloud.di

import com.nvvi9.spoticloud.domain.GetYouTubeVideoItemsUseCase
import com.nvvi9.spoticloud.repositories.base.YouTubeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    fun provideYouTubeVideoItemsUseCase(youTubeRepository: YouTubeRepository): GetYouTubeVideoItemsUseCase =
        GetYouTubeVideoItemsUseCase(youTubeRepository)

}