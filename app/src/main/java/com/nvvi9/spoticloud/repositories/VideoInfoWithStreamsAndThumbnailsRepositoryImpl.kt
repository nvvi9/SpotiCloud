package com.nvvi9.spoticloud.repositories

import com.nvvi9.spoticloud.database.model.VideoInfoWithStreamsAndThumbnails
import com.nvvi9.spoticloud.database.source.base.StreamInfoDataSource
import com.nvvi9.spoticloud.database.source.base.ThumbnailInfoDataSource
import com.nvvi9.spoticloud.database.source.base.VideoInfoDataSource
import com.nvvi9.spoticloud.repositories.base.VideoInfoWithStreamsAndThumbnailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VideoInfoWithStreamsAndThumbnailsRepositoryImpl @Inject constructor(
    private val videoInfoDataSource: VideoInfoDataSource,
    private val streamInfoDataSource: StreamInfoDataSource,
    private val thumbnailInfoDataSource: ThumbnailInfoDataSource
) : VideoInfoWithStreamsAndThumbnailsRepository {

    override suspend fun saveVideoDataWithStreamsAndThumbnails(videoInfoWithStreamsAndThumbnails: VideoInfoWithStreamsAndThumbnails): VideoInfoWithStreamsAndThumbnails {
        val (videoData, streams, thumbnails) = videoInfoWithStreamsAndThumbnails

        return VideoInfoWithStreamsAndThumbnails(
            videoInfoDataSource.insertVideoInfo(videoData),
            streamInfoDataSource.insertStreamInfo(streams),
            thumbnailInfoDataSource.insertThumbnailInfo(thumbnails)
        )
    }

    override suspend fun saveVideoDataWithStreamsAndThumbnails(videoInfoWithStreamsAndThumbnails: List<VideoInfoWithStreamsAndThumbnails>): List<VideoInfoWithStreamsAndThumbnails> =
        videoInfoWithStreamsAndThumbnails.map { saveVideoDataWithStreamsAndThumbnails(it) }

    override fun getVideoInfoWithStreamsAndThumbnails(): Flow<List<VideoInfoWithStreamsAndThumbnails>> =
        videoInfoDataSource.getVideoInfoWithStreamsAndThumbnails()
}