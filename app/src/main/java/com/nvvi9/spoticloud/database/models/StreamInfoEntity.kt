package com.nvvi9.spoticloud.database.models

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.nvvi9.ytstream.model.codecs.AudioCodec
import com.nvvi9.ytstream.model.codecs.VideoCodec
import com.nvvi9.ytstream.model.streams.StreamType

@Entity(tableName = "stream_info", indices = [Index(value = ["url"], unique = true)])
data class StreamInfoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val videoId: String,
    val url: String,
    val itag: Int,
    val type: StreamType,
    val extension: Extension,
    val audioCodec: AudioCodec?,
    val videoCodec: VideoCodec?,
    val quality: Int?,
    val bitrate: Int?,
    val fps: Int?,
    val expiresInSeconds: Long
) {
    enum class Extension {
        MP4, M4A, THREE_GP, FLV, WEBM
    }
}