package com.nvvi9.spoticloud.network.youtube

import com.nvvi9.spoticloud.network.model.YTSearchPartId
import com.nvvi9.spoticloud.network.model.YTVideosPartId
import com.nvvi9.spoticloud.util.Try

interface YouTubeNetworkDataSource {

    suspend fun getPopular(maxResults: Int, pageToken: String? = null): Try<YTVideosPartId>

    suspend fun getFromQuery(
        query: String,
        maxResults: Int,
        pageToken: String? = null
    ): Try<YTSearchPartId>
}