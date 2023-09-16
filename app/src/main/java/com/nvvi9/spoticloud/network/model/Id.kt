package com.nvvi9.spoticloud.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Id(
    val kind: String,
    val videoId: String?,
    val playlistId: String?
)
