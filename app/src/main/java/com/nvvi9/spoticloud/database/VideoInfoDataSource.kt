package com.nvvi9.spoticloud.database

import com.nvvi9.spoticloud.database.models.VideoInfoWithStreams

interface VideoInfoDataSource {
    suspend fun upsertVideoInfoWithStreams(videoInfoWithStreams: VideoInfoWithStreams)
}