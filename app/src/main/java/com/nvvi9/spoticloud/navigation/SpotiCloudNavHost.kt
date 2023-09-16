package com.nvvi9.spoticloud.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.nvvi9.spoticloud.ui.SpotiCloudAppState

@Composable
fun SpotiCloudNavHost(
    appState: SpotiCloudAppState,
    modifier: Modifier = Modifier,
    startDestination: String = youtubeNavigationRoute
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        youtubeScreen()
        playlistScreen()
    }
}