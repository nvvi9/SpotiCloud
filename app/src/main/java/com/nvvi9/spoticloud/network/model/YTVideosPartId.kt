package com.nvvi9.spoticloud.network.model

import kotlinx.serialization.Serializable

@Serializable
data class YTVideosPartId(
    val kind: String,
    val etag: String,
    val items: List<YTPartIdItem>,
    val prevPageToken: String? = null,
    val nextPageToken: String? = null,
    val pageInfo: PageInfo
) {

    @Serializable
    data class YTPartIdItem(
        val kind: String,
        val etag: String,
        val id: String
    )
}