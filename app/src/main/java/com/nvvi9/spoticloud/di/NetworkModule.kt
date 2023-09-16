package com.nvvi9.spoticloud.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.nvvi9.spoticloud.network.retrofit.YouTubeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun provideYouTubeApiService(json: Json): YouTubeApiService = Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl("https://www.googleapis.com/youtube/v3/")
        .build()
        .create(YouTubeApiService::class.java)
}