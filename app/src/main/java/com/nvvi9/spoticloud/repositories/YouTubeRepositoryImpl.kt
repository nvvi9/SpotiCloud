package com.nvvi9.spoticloud.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nvvi9.spoticloud.network.youtube.YouTubeNetworkDataSource
import com.nvvi9.spoticloud.network.ytstream.YTStreamDataSource
import com.nvvi9.spoticloud.repositories.base.YouTubeRepository
import com.nvvi9.spoticloud.repositories.paging.VideoDetailsPagingSource
import com.nvvi9.ytstream.model.VideoDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class YouTubeRepositoryImpl @Inject constructor(
    private val youTubeNetworkDataSource: YouTubeNetworkDataSource,
    private val ytStreamDataSource: YTStreamDataSource
) : YouTubeRepository {

    override fun getVideoDetails(): Flow<PagingData<VideoDetails>> =
        Pager(PagingConfig(4)) {
            VideoDetailsPagingSource(youTubeNetworkDataSource, ytStreamDataSource)
        }.flow
}