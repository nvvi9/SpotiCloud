package com.nvvi9.spoticloud.ui.icon

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.QueueMusic
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import com.nvvi9.spoticloud.R

object SpotiCloudIcons {
    val Youtube = Icon.DrawableResourceIcon(R.drawable.ic_youtube)
    val Playlist = Icon.ImageVectorIcon(Icons.Outlined.QueueMusic)
}

sealed interface Icon {
    @JvmInline
    value class ImageVectorIcon(val imageVector: ImageVector) : Icon

    @JvmInline
    value class DrawableResourceIcon(@DrawableRes val id: Int) : Icon
}

fun Icon.asComposeIcon(): @Composable () -> Unit = when (this) {
    is Icon.DrawableResourceIcon -> {
        { Icon(painter = painterResource(id = id), contentDescription = null) }
    }

    is Icon.ImageVectorIcon -> {
        { Icon(imageVector = imageVector, contentDescription = null) }
    }
}