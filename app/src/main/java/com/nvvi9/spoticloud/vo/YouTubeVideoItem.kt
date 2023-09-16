package com.nvvi9.spoticloud.vo

data class YouTubeVideoItem(
    val id: String,
    val title: String,
    val thumbnailUri: String,
    val channel: String,
    val viewCount: Long,
    val durationSeconds: Long
)