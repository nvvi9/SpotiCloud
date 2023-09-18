package com.nvvi9.spoticloud.database

import com.nvvi9.spoticloud.database.dao.StreamInfoDao
import com.nvvi9.spoticloud.database.dao.VideoInfoDao
import com.nvvi9.spoticloud.database.models.VideoInfoWithStreams
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VideoInfoDataSourceImpl @Inject constructor(
    private val videoInfoDao: VideoInfoDao,
    private val streamInfoDao: StreamInfoDao
) : VideoInfoDataSource {

    override suspend fun upsertVideoInfoWithStreams(videoInfoWithStreams: VideoInfoWithStreams) {
        withContext(Dispatchers.IO) {
            videoInfoDao.upsertVideoInfo(videoInfoWithStreams.videoInfoEntity)
            streamInfoDao.upsertStreamInfo(videoInfoWithStreams.streams)
        }
    }
}