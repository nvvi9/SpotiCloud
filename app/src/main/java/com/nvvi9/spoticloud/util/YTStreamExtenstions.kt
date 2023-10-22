package com.nvvi9.spoticloud.util

import com.nvvi9.spoticloud.StreamInfo
import com.nvvi9.spoticloud.ThumbnailInfo
import com.nvvi9.spoticloud.VideoInfo
import com.nvvi9.spoticloud.database.model.VideoInfoWithStreamsAndThumbnails
import com.nvvi9.ytstream.model.Thumbnail
import com.nvvi9.ytstream.model.VideoData
import com.nvvi9.ytstream.model.VideoDetails
import com.nvvi9.ytstream.model.streams.Stream

fun VideoDetails.toVideoInfo(): VideoInfo = VideoInfo(
    id = id,
    title = title,
    channel = channel,
    channelId = channelId,
    duration = durationSeconds,
    viewCount = viewCount
)

fun Stream.toStreamInfo(videoId: String): StreamInfo? = streamDetails.expiresInSeconds?.let {
    StreamInfo(
        id = 0,
        url = url,
        itag = streamDetails.itag.toLong(),
        streamType = streamDetails.type,
        extension = streamDetails.extension,
        audioCodec = streamDetails.audioCodec,
        videoCodec = streamDetails.videoCodec,
        quality = streamDetails.quality?.toLong(),
        bitrate = streamDetails.bitrate?.toLong(),
        fps = streamDetails.fps?.toLong(),
        expiresInSeconds = it,
        videoId = videoId
    )
}

fun Thumbnail.toThumbnailInfo(videoId: String): ThumbnailInfo = ThumbnailInfo(
    id = 0,
    width = width.toLong(),
    height = height.toLong(),
    url = url,
    videoId = videoId
)

fun VideoData.toVideoInfoWithStreamsAndThumbnails(): VideoInfoWithStreamsAndThumbnails =
    VideoInfoWithStreamsAndThumbnails(
        videoInfo = videoDetails.toVideoInfo(),
        streams = streams.mapNotNull { it.toStreamInfo(videoDetails.id) },
        thumbnails = videoDetails.thumbnails.map { it.toThumbnailInfo(videoDetails.id) }
    )

fun List<VideoData>.toVideoInfoWithStreamsAndThumbnails(): List<VideoInfoWithStreamsAndThumbnails> =
    map { it.toVideoInfoWithStreamsAndThumbnails() }