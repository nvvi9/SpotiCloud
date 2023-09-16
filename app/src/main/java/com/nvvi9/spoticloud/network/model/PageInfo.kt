package com.nvvi9.spoticloud.network.model

import kotlinx.serialization.Serializable

@Serializable
data class PageInfo(
    val totalResults: Int,
    val resultsPerPage: Int
)