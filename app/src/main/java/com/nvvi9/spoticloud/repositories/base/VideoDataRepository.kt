package com.nvvi9.spoticloud.repositories.base

import com.nvvi9.spoticloud.util.Try
import com.nvvi9.ytstream.model.VideoData

interface VideoDataRepository {
    suspend fun extractVideoData(videoId: String): Try<VideoData>
    suspend fun extractVideoData(videoIds: List<String>): Try<List<VideoData>>
}