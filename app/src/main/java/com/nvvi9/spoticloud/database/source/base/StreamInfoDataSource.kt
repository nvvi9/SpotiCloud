package com.nvvi9.spoticloud.database.source.base

import com.nvvi9.spoticloud.StreamInfo

interface StreamInfoDataSource {
    suspend fun insertStreamInfo(streamInfo: StreamInfo): StreamInfo
    suspend fun insertStreamInfo(streamData: List<StreamInfo>): List<StreamInfo>
    suspend fun getStreamInfoByVideoId(videoId: String): List<StreamInfo>
    suspend fun updateStreamUrl(id: Long, url: String, expiresInSeconds: Long)
}