package com.nvvi9.spoticloud.repositories

import com.nvvi9.spoticloud.network.ytstream.YTStreamDataSource
import com.nvvi9.spoticloud.repositories.base.VideoDataRepository
import com.nvvi9.spoticloud.util.Try
import com.nvvi9.spoticloud.util.unzip
import com.nvvi9.ytstream.model.VideoData
import javax.inject.Inject

class VideoDataRepositoryImpl @Inject constructor(
    private val ytStreamDataSource: YTStreamDataSource
) : VideoDataRepository {

    override suspend fun extractVideoData(videoId: String): Try<VideoData> =
        ytStreamDataSource.getVideoData(videoId)

    override suspend fun extractVideoData(videoIds: List<String>): Try<List<VideoData>> =
        ytStreamDataSource.getVideoData(videoIds).unzip()
}