package com.nvvi9.spoticloud.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.nvvi9.spoticloud.ui.youtube.YoutubeRoute

const val youtubeNavigationRoute = "youtube_route"

fun NavController.navigateToYoutube(navOptions: NavOptions? = null) {
    this.navigate(youtubeNavigationRoute, navOptions)
}

fun NavGraphBuilder.youtubeScreen() {
    composable(route = youtubeNavigationRoute) {
        YoutubeRoute()
    }
}