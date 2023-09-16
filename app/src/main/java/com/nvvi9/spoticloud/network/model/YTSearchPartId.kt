package com.nvvi9.spoticloud.network.model

import kotlinx.serialization.Serializable

@Serializable
data class YTSearchPartId(
    val kind: String,
    val etag: String,
    val nextPageToken: String?,
    val prevPageToken: String?,
    val regionCode: String,
    val pageInfo: PageInfo,
    val items: List<YTSearchPartIdItem>
) {

    @Serializable
    data class YTSearchPartIdItem(
        val kind: String,
        val etag: String,
        val id: Id
    )
}