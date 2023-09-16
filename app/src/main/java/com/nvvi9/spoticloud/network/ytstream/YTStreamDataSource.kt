package com.nvvi9.spoticloud.network.ytstream

import com.nvvi9.spoticloud.util.Try
import com.nvvi9.ytstream.model.VideoData
import com.nvvi9.ytstream.model.VideoDetails

interface YTStreamDataSource {
    suspend fun getVideoData(videoId: String): Try<VideoData>
    suspend fun getVideoData(videoIds: List<String>): List<Try<VideoData>>
    suspend fun getVideoDetails(videoId: String): Try<VideoDetails>
    suspend fun getVideoDetails(videoIds: List<String>): List<Try<VideoDetails>>
}