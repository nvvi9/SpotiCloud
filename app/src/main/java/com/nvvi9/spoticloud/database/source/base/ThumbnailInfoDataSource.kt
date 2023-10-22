package com.nvvi9.spoticloud.database.source.base

import com.nvvi9.spoticloud.ThumbnailInfo

interface ThumbnailInfoDataSource {
    suspend fun insertThumbnailInfo(thumbnailInfo: ThumbnailInfo): ThumbnailInfo
    suspend fun insertThumbnailInfo(thumbnailData: List<ThumbnailInfo>): List<ThumbnailInfo>
}