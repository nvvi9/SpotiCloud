package com.nvvi9.spoticloud.domain

import androidx.paging.PagingData
import androidx.paging.map
import com.nvvi9.spoticloud.repositories.base.YouTubeRepository
import com.nvvi9.spoticloud.vo.YouTubeVideoItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetYouTubeVideoItemsUseCase @Inject constructor(private val youTubeRepository: YouTubeRepository) {

    operator fun invoke(): Flow<PagingData<YouTubeVideoItem>> = youTubeRepository.getVideoDetails().map { pagingData ->
        pagingData.map { videoDetails ->
            YouTubeVideoItem(
                id = videoDetails.id,
                title = videoDetails.title,
                thumbnailUri = videoDetails.thumbnails.maxBy { it.height }.url,
                channel = videoDetails.channel,
                viewCount = videoDetails.viewCount,
                durationSeconds = videoDetails.durationSeconds
            )
        }
    }
}