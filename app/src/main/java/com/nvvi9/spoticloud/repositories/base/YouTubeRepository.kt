package com.nvvi9.spoticloud.repositories.base

import androidx.paging.PagingData
import com.nvvi9.ytstream.model.VideoDetails
import kotlinx.coroutines.flow.Flow

interface YouTubeRepository {

    fun getVideoDetails(): Flow<PagingData<VideoDetails>>
}