package com.nvvi9.spoticloud.ui.youtube

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.nvvi9.spoticloud.vo.YouTubeVideoItem

@Composable
fun YoutubeRoute(
    modifier: Modifier = Modifier,
) {
    YouTubeScreen(modifier = modifier)
}

@Composable
fun YouTubeScreen(
    modifier: Modifier = Modifier,
    viewModel: YouTubeViewModel = hiltViewModel()
) {
    val state = rememberLazyGridState()
    val youTubeVideoItems: LazyPagingItems<YouTubeVideoItem> =
        viewModel.youTubeVideoItems.collectAsLazyPagingItems()

    Box(modifier = modifier.fillMaxSize()) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(300.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            state = state
        ) {
            items(youTubeVideoItems.itemCount) { index ->
                YouTubeVideoItemCard(youTubeVideoItem = youTubeVideoItems[index]!!)
            }
        }
    }
}