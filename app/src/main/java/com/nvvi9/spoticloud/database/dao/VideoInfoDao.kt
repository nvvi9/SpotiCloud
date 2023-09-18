package com.nvvi9.spoticloud.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.nvvi9.spoticloud.database.models.VideoInfoEntity
import com.nvvi9.spoticloud.database.models.VideoInfoWithStreams
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoInfoDao {

    @Upsert
    suspend fun upsertVideoInfo(videoInfoEntity: VideoInfoEntity)

    @Upsert
    suspend fun upsertVideoInfo(videoInfoEntities: List<VideoInfoEntity>)

    @Query("SELECT * FROM video_info")
    fun getVideoInfo(): Flow<List<VideoInfoEntity>>

    @Transaction
    @Query("SELECT * FROM video_info")
    fun getVideoInfoWithStreams(): Flow<List<VideoInfoWithStreams>>

    @Query("DELETE FROM video_info WHERE id = :id")
    suspend fun deleteVideoInfo(id: String)
}