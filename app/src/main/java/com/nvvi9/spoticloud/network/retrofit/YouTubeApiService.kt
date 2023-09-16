package com.nvvi9.spoticloud.network.retrofit

import com.nvvi9.spoticloud.BuildConfig
import com.nvvi9.spoticloud.network.model.YTSearchPartId
import com.nvvi9.spoticloud.network.model.YTVideosPartId
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApiService {

    @GET("search?key=${BuildConfig.YOUTUBE_API_KEY}&part=id&type=video")
    suspend fun getYTSearchPartId(
        @Query("q") q: String,
        @Query("maxResults") maxResults: Int,
        @Query("pageToken") pageToken: String?
    ): YTSearchPartId

    @GET("videos?key=${BuildConfig.YOUTUBE_API_KEY}&part=id&chart=mostPopular&videoCategoryId=0")
    suspend fun getYTVideosIdResponse(
        @Query("maxResults") maxResults: Int,
        @Query("pageToken") pageToken: String? = null
    ): YTVideosPartId
}