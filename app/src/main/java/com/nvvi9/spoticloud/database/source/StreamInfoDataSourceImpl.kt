package com.nvvi9.spoticloud.database.source

import com.nvvi9.spoticloud.StreamInfo
import com.nvvi9.spoticloud.StreamInfoQueries
import com.nvvi9.spoticloud.database.source.base.StreamInfoDataSource
import com.nvvi9.spoticloud.util.Dispatcher
import com.nvvi9.spoticloud.util.SpotiCloudDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StreamInfoDataSourceImpl @Inject constructor(
    private val streamInfoQueries: StreamInfoQueries,
    @Dispatcher(SpotiCloudDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : StreamInfoDataSource {

    override suspend fun insertStreamInfo(streamInfo: StreamInfo): StreamInfo =
        withContext(ioDispatcher) {
            streamInfoQueries.transactionWithResult {
                streamInfoQueries.insertStreamInfo(
                    videoId = streamInfo.videoId,
                    url = streamInfo.url,
                    itag = streamInfo.itag,
                    streamType = streamInfo.streamType,
                    extension = streamInfo.extension,
                    audioCodec = streamInfo.audioCodec,
                    videoCodec = streamInfo.videoCodec,
                    quality = streamInfo.quality,
                    bitrate = streamInfo.bitrate,
                    fps = streamInfo.fps,
                    expiresInSeconds = streamInfo.expiresInSeconds
                ).executeAsOne()
            }
        }

    override suspend fun insertStreamInfo(streamData: List<StreamInfo>): List<StreamInfo> =
        withContext(ioDispatcher) {
            streamInfoQueries.transactionWithResult {
                streamData.map {
                    streamInfoQueries.insertStreamInfo(
                        videoId = it.videoId,
                        url = it.url,
                        itag = it.itag,
                        streamType = it.streamType,
                        extension = it.extension,
                        audioCodec = it.audioCodec,
                        videoCodec = it.videoCodec,
                        quality = it.quality,
                        bitrate = it.bitrate,
                        fps = it.fps,
                        expiresInSeconds = it.expiresInSeconds
                    ).executeAsOne()
                }
            }
        }

    override suspend fun getStreamInfoByVideoId(videoId: String): List<StreamInfo> =
        withContext(ioDispatcher) {
            streamInfoQueries.selectByVideoId(videoId).executeAsList()
        }

    override suspend fun updateStreamUrl(id: Long, url: String, expiresInSeconds: Long) {
        withContext(ioDispatcher) {
            streamInfoQueries.updateUrl(url, expiresInSeconds, id)
        }
    }
}