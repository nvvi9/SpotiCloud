package com.nvvi9.spoticloud.database.source.base

import com.nvvi9.spoticloud.VideoInfo
import com.nvvi9.spoticloud.database.model.VideoInfoWithStreamsAndThumbnails
import kotlinx.coroutines.flow.Flow

interface VideoInfoDataSource {
    suspend fun insertVideoInfo(videoInfo: VideoInfo): VideoInfo
    suspend fun insertVideoInfo(videoInfo: List<VideoInfo>): List<VideoInfo>
    suspend fun deleteVideoInfo(id: String)
    fun getVideoInfoWithStreamsAndThumbnails(): Flow<List<VideoInfoWithStreamsAndThumbnails>>
}