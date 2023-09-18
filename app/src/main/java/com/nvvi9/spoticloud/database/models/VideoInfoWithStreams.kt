package com.nvvi9.spoticloud.database.models

import androidx.room.Embedded
import androidx.room.Relation

data class VideoInfoWithStreams(
    @Embedded val videoInfoEntity: VideoInfoEntity,
    @Relation(parentColumn = "id", entityColumn = "videoId")
    val streams: List<StreamInfoEntity>
)