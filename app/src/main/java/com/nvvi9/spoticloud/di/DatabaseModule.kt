package com.nvvi9.spoticloud.di

import android.content.Context
import androidx.room.Room
import com.nvvi9.spoticloud.database.SpotiCloudDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideSpotiCloudDatabase(@ApplicationContext context: Context): SpotiCloudDatabase =
        Room
            .databaseBuilder(context, SpotiCloudDatabase::class.java, "spoticloud.db")
            .build()
}