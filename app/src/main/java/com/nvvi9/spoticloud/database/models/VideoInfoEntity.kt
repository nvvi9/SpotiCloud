package com.nvvi9.spoticloud.database.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "video_info", indices = [Index(value = ["id"], unique = true)])
data class VideoInfoEntity(
    @PrimaryKey val id: String,
    val title: String,
    val channel: String,
    val channelId: String,
    val duration: Long,
    val viewCount: Long
)