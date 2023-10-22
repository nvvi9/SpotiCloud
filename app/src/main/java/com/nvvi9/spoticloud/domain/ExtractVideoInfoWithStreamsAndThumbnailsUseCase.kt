package com.nvvi9.spoticloud.domain

import com.nvvi9.spoticloud.database.model.VideoInfoWithStreamsAndThumbnails
import com.nvvi9.spoticloud.repositories.base.VideoDataRepository
import com.nvvi9.spoticloud.repositories.base.VideoInfoWithStreamsAndThumbnailsRepository
import com.nvvi9.spoticloud.util.UiState
import com.nvvi9.spoticloud.util.asUiStateFlow
import com.nvvi9.spoticloud.util.map
import com.nvvi9.spoticloud.util.toVideoInfoWithStreamsAndThumbnails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ExtractVideoInfoWithStreamsAndThumbnailsUseCase @Inject constructor(
    private val videoInfoWithStreamsAndThumbnailsRepository: VideoInfoWithStreamsAndThumbnailsRepository,
    private val videoDataRepository: VideoDataRepository
) {

    operator fun invoke(videoId: String): Flow<UiState<VideoInfoWithStreamsAndThumbnails>> =
        extractVideoDataWithStreamsAndThumbnails(listOf(videoId))
            .map { listTry -> listTry.map { it.first() } }
            .asUiStateFlow()

    operator fun invoke(videoIds: List<String>): Flow<UiState<List<VideoInfoWithStreamsAndThumbnails>>> =
        extractVideoDataWithStreamsAndThumbnails(videoIds)
            .asUiStateFlow()

    private fun extractVideoDataWithStreamsAndThumbnails(videoIds: List<String>) = flow {
        val videoInfoWithStreamsAndThumbnailsList =
            videoDataRepository.extractVideoData(videoIds)
                .map { it.toVideoInfoWithStreamsAndThumbnails() }
                .map {
                    videoInfoWithStreamsAndThumbnailsRepository
                        .saveVideoDataWithStreamsAndThumbnails(it)
                }

        emit(videoInfoWithStreamsAndThumbnailsList)
    }
}