package com.nvvi9.spoticloud.repositories.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nvvi9.spoticloud.network.youtube.YouTubeNetworkDataSource
import com.nvvi9.spoticloud.network.ytstream.YTStreamDataSource
import com.nvvi9.spoticloud.util.fold
import com.nvvi9.spoticloud.util.map
import com.nvvi9.spoticloud.util.valueOrNull
import com.nvvi9.ytstream.model.VideoDetails
import javax.inject.Inject

class VideoDetailsPagingSource @Inject constructor(
    private val youTubeNetworkDataSource: YouTubeNetworkDataSource,
    private val ytStreamDataSource: YTStreamDataSource
) : PagingSource<String, VideoDetails>() {

    override fun getRefreshKey(state: PagingState<String, VideoDetails>): String? = null

    override suspend fun load(params: LoadParams<String>): LoadResult<String, VideoDetails> =
        youTubeNetworkDataSource.getPopular(params.loadSize, params.key)
            .map { ytVideosPartId ->
                val items = ytStreamDataSource.getVideoDetails(ytVideosPartId.items.map { it.id })
                    .mapNotNull { it.valueOrNull }
                LoadResult.Page(items, ytVideosPartId.prevPageToken, ytVideosPartId.nextPageToken)
            }.fold({ it }, { LoadResult.Error(it) })
}