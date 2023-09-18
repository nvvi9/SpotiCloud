package com.nvvi9.spoticloud.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nvvi9.spoticloud.database.converters.InstantConverter
import com.nvvi9.spoticloud.database.dao.StreamInfoDao
import com.nvvi9.spoticloud.database.dao.VideoInfoDao
import com.nvvi9.spoticloud.database.models.StreamInfoEntity
import com.nvvi9.spoticloud.database.models.VideoInfoEntity

@Database(
    entities = [VideoInfoEntity::class, StreamInfoEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(InstantConverter::class)
abstract class SpotiCloudDatabase : RoomDatabase() {
    abstract fun videoInfoDao(): VideoInfoDao
    abstract fun streamInfoDao(): StreamInfoDao
}