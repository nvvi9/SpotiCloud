package com.nvvi9.spoticloud.network.ytstream

import com.nvvi9.spoticloud.util.Dispatcher
import com.nvvi9.spoticloud.util.SpotiCloudDispatchers
import com.nvvi9.spoticloud.util.Try
import com.nvvi9.spoticloud.util.asTry
import com.nvvi9.ytstream.YTStream
import com.nvvi9.ytstream.model.VideoData
import com.nvvi9.ytstream.model.VideoDetails
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.withContext
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class YTStreamDataSourceImpl @Inject constructor(
    private val ytStream: YTStream,
    @Dispatcher(SpotiCloudDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : YTStreamDataSource {

    override suspend fun getVideoData(videoId: String): Try<VideoData> = withContext(ioDispatcher) {
        ytStream.extractVideoData(videoId).asTry()
    }

    override suspend fun getVideoData(videoIds: List<String>): List<Try<VideoData>> =
        videoIds.asFlow()
            .flatMapMerge(concurrency = videoIds.size) { videoId ->
                flow { emit(ytStream.extractVideoData(videoId)) }
            }
            .map { it.asTry() }
            .flowOn(ioDispatcher)
            .toList()

    override suspend fun getVideoDetails(videoId: String): Try<VideoDetails> =
        withContext(ioDispatcher) {
            ytStream.extractVideoDetails(videoId).asTry()
        }

    override suspend fun getVideoDetails(videoIds: List<String>): List<Try<VideoDetails>> =
        videoIds.asFlow()
            .flatMapMerge(videoIds.size) { videoId ->
                flow { emit(ytStream.extractVideoDetails(videoId)) }
            }
            .map { it.asTry() }
            .flowOn(ioDispatcher)
            .toList()
}