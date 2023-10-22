package com.nvvi9.spoticloud.database.source

import com.nvvi9.spoticloud.ThumbnailInfo
import com.nvvi9.spoticloud.ThumbnailInfoQueries
import com.nvvi9.spoticloud.database.source.base.ThumbnailInfoDataSource
import com.nvvi9.spoticloud.util.Dispatcher
import com.nvvi9.spoticloud.util.SpotiCloudDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ThumbnailInfoDataSourceImpl @Inject constructor(
    private val thumbnailInfoQueries: ThumbnailInfoQueries,
    @Dispatcher(SpotiCloudDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : ThumbnailInfoDataSource {

    override suspend fun insertThumbnailInfo(thumbnailInfo: ThumbnailInfo): ThumbnailInfo =
        withContext(ioDispatcher) {
            thumbnailInfoQueries.transactionWithResult {
                thumbnailInfoQueries.insertThumbnailInfo(
                    thumbnailInfo.width,
                    thumbnailInfo.height,
                    thumbnailInfo.url,
                    thumbnailInfo.videoId
                ).executeAsOne()
            }
        }

    override suspend fun insertThumbnailInfo(thumbnailData: List<ThumbnailInfo>): List<ThumbnailInfo> =
        withContext(ioDispatcher) {
            thumbnailInfoQueries.transactionWithResult {
                thumbnailData.map {
                    thumbnailInfoQueries.insertThumbnailInfo(
                        it.width,
                        it.height,
                        it.url,
                        it.videoId
                    ).executeAsOne()
                }
            }
        }
}