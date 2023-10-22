package com.nvvi9.spoticloud.database.model

import com.nvvi9.spoticloud.StreamInfo
import com.nvvi9.spoticloud.ThumbnailInfo
import com.nvvi9.spoticloud.VideoInfo

data class VideoInfoWithStreamsAndThumbnails(
    val videoInfo: VideoInfo,
    val streams: List<StreamInfo>,
    val thumbnails: List<ThumbnailInfo>
)