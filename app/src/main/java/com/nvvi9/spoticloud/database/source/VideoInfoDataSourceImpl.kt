package com.nvvi9.spoticloud.database.source

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.nvvi9.spoticloud.StreamInfo
import com.nvvi9.spoticloud.ThumbnailInfo
import com.nvvi9.spoticloud.VideoInfo
import com.nvvi9.spoticloud.VideoInfoQueries
import com.nvvi9.spoticloud.database.model.VideoInfoWithStreamsAndThumbnails
import com.nvvi9.spoticloud.database.source.base.VideoInfoDataSource
import com.nvvi9.spoticloud.util.Dispatcher
import com.nvvi9.spoticloud.util.SpotiCloudDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VideoInfoDataSourceImpl @Inject constructor(
    private val videoInfoQueries: VideoInfoQueries,
    @Dispatcher(SpotiCloudDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : VideoInfoDataSource {

    override suspend fun insertVideoInfo(videoInfo: VideoInfo): VideoInfo =
        withContext(ioDispatcher) {
            videoInfoQueries.insertVideoInfo(
                id = videoInfo.id,
                title = videoInfo.title,
                channel = videoInfo.channel,
                channelId = videoInfo.channelId,
                duration = videoInfo.duration,
                viewCount = videoInfo.viewCount
            ).executeAsOne()
        }

    override suspend fun insertVideoInfo(videoInfo: List<VideoInfo>): List<VideoInfo> =
        withContext(ioDispatcher) {
            videoInfoQueries.transactionWithResult {
                videoInfo.map {
                    videoInfoQueries.insertVideoInfo(
                        id = it.id,
                        title = it.title,
                        channel = it.channel,
                        channelId = it.channelId,
                        duration = it.duration,
                        viewCount = it.viewCount
                    ).executeAsOne()
                }
            }
        }

    override suspend fun deleteVideoInfo(id: String) {
        withContext(ioDispatcher) {
            videoInfoQueries.deleteVideoInfo(id)
        }
    }

    override fun getVideoInfoWithStreamsAndThumbnails(): Flow<List<VideoInfoWithStreamsAndThumbnails>> =
        videoInfoQueries.selectVideoInfoWithStreamsAndThumbnails()
            .asFlow()
            .mapToList(ioDispatcher)
            .map { getVideoDataWithStreams ->
                getVideoDataWithStreams.groupBy { it.id }
                    .map { (videoId, getVideoInfoWithStreams) ->
                        val videoInfo = getVideoInfoWithStreams.first().let {
                            VideoInfo(
                                id = it.id,
                                title = it.title,
                                channel = it.channel,
                                channelId = it.channelId,
                                duration = it.duration,
                                viewCount = it.viewCount
                            )
                        }

                        val (streams, thumbnails) = getVideoInfoWithStreams.map {
                            StreamInfo(
                                id = it.streamId,
                                url = it.streamUrl,
                                itag = it.itag,
                                streamType = it.streamType,
                                extension = it.extension,
                                audioCodec = it.audioCodec,
                                videoCodec = it.videoCodec,
                                quality = it.quality,
                                bitrate = it.bitrate,
                                fps = it.fps,
                                expiresInSeconds = it.expiresInSeconds,
                                videoId = videoId,
                            ) to ThumbnailInfo(
                                id = it.thumbnailId,
                                width = it.width,
                                height = it.height,
                                url = it.thumbnailUrl,
                                videoId = videoId
                            )
                        }.unzip()

                        VideoInfoWithStreamsAndThumbnails(videoInfo, streams, thumbnails)
                    }
            }
}