package com.nvvi9.spoticloud.repositories.base

import com.nvvi9.spoticloud.database.model.VideoInfoWithStreamsAndThumbnails
import kotlinx.coroutines.flow.Flow

interface VideoInfoWithStreamsAndThumbnailsRepository {
    suspend fun saveVideoDataWithStreamsAndThumbnails(videoInfoWithStreamsAndThumbnails: VideoInfoWithStreamsAndThumbnails): VideoInfoWithStreamsAndThumbnails
    suspend fun saveVideoDataWithStreamsAndThumbnails(videoInfoWithStreamsAndThumbnails: List<VideoInfoWithStreamsAndThumbnails>): List<VideoInfoWithStreamsAndThumbnails>
    fun getVideoInfoWithStreamsAndThumbnails(): Flow<List<VideoInfoWithStreamsAndThumbnails>>
}