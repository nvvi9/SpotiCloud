package com.nvvi9.spoticloud.ui.youtube

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.nvvi9.spoticloud.vo.YouTubeVideoItem

class YouTubeVideoItemPreviewParameterProvider : PreviewParameterProvider<List<YouTubeVideoItem>> {

    override val values: Sequence<List<YouTubeVideoItem>>
        get() = sequenceOf(PreviewParameterData.youTubeVideoItems)
}

object PreviewParameterData {

    val youTubeVideoItems = listOf(
        YouTubeVideoItem(
            "id",
            "title",
            "https://cdn.pixabay.com/photo/2016/11/29/05/45/astronomy-1867616_1280.jpg",
            "channel",
            1234,
            1234
        )
    )
}