package com.nvvi9.spoticloud.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.nvvi9.spoticloud.ui.playlist.PlaylistRoute

const val playlistRoute = "playlist_route"

fun NavController.navigateToPlaylist(navOptions: NavOptions? = null) {
    this.navigate(playlistRoute, navOptions)
}

fun NavGraphBuilder.playlistScreen() {
    composable(route = playlistRoute) {
        PlaylistRoute()
    }
}