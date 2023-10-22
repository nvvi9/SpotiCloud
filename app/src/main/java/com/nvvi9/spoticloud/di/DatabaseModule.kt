package com.nvvi9.spoticloud.di

import android.content.Context
import app.cash.sqldelight.EnumColumnAdapter
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.nvvi9.spoticloud.SpotiCloudDatabase
import com.nvvi9.spoticloud.StreamInfo
import com.nvvi9.spoticloud.StreamInfoQueries
import com.nvvi9.spoticloud.ThumbnailInfoQueries
import com.nvvi9.spoticloud.VideoInfoQueries
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideSqlDriver(context: Context): SqlDriver =
        AndroidSqliteDriver(
            schema = SpotiCloudDatabase.Schema,
            context = context,
            name = "spoticloud.db"
        )

    @Provides
    @Singleton
    fun provideSpotiCloudDatabase(driver: SqlDriver): SpotiCloudDatabase =
        SpotiCloudDatabase(
            driver,
            StreamInfo.Adapter(
                EnumColumnAdapter(),
                EnumColumnAdapter(),
                EnumColumnAdapter(),
                EnumColumnAdapter(),
            )
        )

    @Provides
    @Singleton
    fun provideVideoInfoQueries(database: SpotiCloudDatabase): VideoInfoQueries =
        database.videoInfoQueries

    @Provides
    @Singleton
    fun provideStreamInfoQueries(database: SpotiCloudDatabase): StreamInfoQueries =
        database.streamInfoQueries

    @Provides
    @Singleton
    fun provideThumbnailInfoQueries(database: SpotiCloudDatabase): ThumbnailInfoQueries =
        database.thumbnailInfoQueries
}