package com.nvvi9.spoticloud.navigation

import com.nvvi9.spoticloud.R
import com.nvvi9.spoticloud.ui.icon.Icon
import com.nvvi9.spoticloud.ui.icon.SpotiCloudIcons

enum class TopLevelDestination(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val iconTextId: Int,
    val titleTextId: Int
) {
    YOUTUBE(
        selectedIcon = SpotiCloudIcons.Youtube,
        unselectedIcon = SpotiCloudIcons.Youtube,
        iconTextId = R.string.youtube,
        titleTextId = R.string.youtube
    ),
    PLAYLIST(
        selectedIcon = SpotiCloudIcons.Playlist,
        unselectedIcon = SpotiCloudIcons.Playlist,
        iconTextId = R.string.playlist,
        titleTextId = R.string.playlist
    )
}