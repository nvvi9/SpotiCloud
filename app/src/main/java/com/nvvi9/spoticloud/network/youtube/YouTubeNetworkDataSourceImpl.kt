package com.nvvi9.spoticloud.network.youtube

import com.nvvi9.spoticloud.network.model.YTSearchPartId
import com.nvvi9.spoticloud.network.model.YTVideosPartId
import com.nvvi9.spoticloud.network.retrofit.YouTubeApiService
import com.nvvi9.spoticloud.util.Dispatcher
import com.nvvi9.spoticloud.util.SpotiCloudDispatchers
import com.nvvi9.spoticloud.util.Try
import com.nvvi9.spoticloud.util.runSafely
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class YouTubeNetworkDataSourceImpl @Inject constructor(
    private val youTubeApiService: YouTubeApiService,
    @Dispatcher(SpotiCloudDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : YouTubeNetworkDataSource {

    override suspend fun getPopular(maxResults: Int, pageToken: String?): Try<YTVideosPartId> =
        withContext(ioDispatcher) {
            runSafely {
                youTubeApiService.getYTVideosIdResponse(maxResults, pageToken)
            }
        }

    override suspend fun getFromQuery(
        query: String,
        maxResults: Int,
        pageToken: String?
    ): Try<YTSearchPartId> = withContext(ioDispatcher) {
        runSafely {
            youTubeApiService.getYTSearchPartId(query, maxResults, pageToken)
        }
    }
}