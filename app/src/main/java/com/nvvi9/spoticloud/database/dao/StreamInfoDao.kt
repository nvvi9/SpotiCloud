package com.nvvi9.spoticloud.database.dao

import androidx.room.Dao
import androidx.room.Upsert
import com.nvvi9.spoticloud.database.models.StreamInfoEntity

@Dao
interface StreamInfoDao {

    @Upsert
    suspend fun upsertStreamInfo(streamInfoEntity: StreamInfoEntity)

    @Upsert
    suspend fun upsertStreamInfo(streamInfoEntities: List<StreamInfoEntity>)
}