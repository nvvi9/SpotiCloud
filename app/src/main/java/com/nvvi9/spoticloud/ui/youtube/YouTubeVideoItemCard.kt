package com.nvvi9.spoticloud.ui.youtube

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.nvvi9.spoticloud.R
import com.nvvi9.spoticloud.ui.theme.SpotiCloudTheme
import com.nvvi9.spoticloud.vo.YouTubeVideoItem

@Composable
fun YouTubeVideoItemCard(youTubeVideoItem: YouTubeVideoItem) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column {
            Row { YouTubeVideoItemThumbnail(thumbnailUrl = youTubeVideoItem.thumbnailUri) }
            Box(modifier = Modifier.padding(16.dp)) {
                Column {
                    Spacer(modifier = Modifier.height(12.dp))
                    Row {
                        Text(
                            text = youTubeVideoItem.title,
                            style = MaterialTheme.typography.headlineSmall,
                            modifier = Modifier.fillMaxWidth(0.8f)
                        )
                    }
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = youTubeVideoItem.channel,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        }
    }
}

@Composable
fun YouTubeVideoItemThumbnail(thumbnailUrl: String) {
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }

    val imageLoader = rememberAsyncImagePainter(
        model = thumbnailUrl,
        onState = {
            isLoading = it is AsyncImagePainter.State.Loading
            isError = it is AsyncImagePainter.State.Error
        }
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(180.dp, 300.dp),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(80.dp),
                color = MaterialTheme.colorScheme.tertiary
            )
        }

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(180.dp, 300.dp),
            contentScale = ContentScale.Crop,
            painter = if (!isError) imageLoader else painterResource(id = R.drawable.ic_youtube),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun YouTubeVideoItemCardPreview(@PreviewParameter(YouTubeVideoItemPreviewParameterProvider::class) youTubeVideoItems: List<YouTubeVideoItem>) {
    SpotiCloudTheme {
        Surface {
            YouTubeVideoItemCard(youTubeVideoItem = youTubeVideoItems[0])
        }
    }
}