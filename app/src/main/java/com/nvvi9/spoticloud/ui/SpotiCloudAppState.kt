package com.nvvi9.spoticloud.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.nvvi9.spoticloud.navigation.TopLevelDestination
import com.nvvi9.spoticloud.navigation.navigateToPlaylist
import com.nvvi9.spoticloud.navigation.navigateToYoutube
import com.nvvi9.spoticloud.navigation.playlistRoute
import com.nvvi9.spoticloud.navigation.youtubeNavigationRoute

@Composable
fun rememberSpotiCloudState(
    windowSizeClass: WindowSizeClass,
    navController: NavHostController = rememberNavController()
): SpotiCloudAppState = remember(navController, windowSizeClass) {
    SpotiCloudAppState(navController, windowSizeClass)
}

@Stable
class SpotiCloudAppState(
    val navController: NavHostController,
    val windowSizeClass: WindowSizeClass
) {

    val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            playlistRoute -> TopLevelDestination.PLAYLIST
            youtubeNavigationRoute -> TopLevelDestination.YOUTUBE
            else -> null
        }

    val shouldShowBottomBar: Boolean
        get() = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    val shouldShowNavRail: Boolean
        get() = !shouldShowBottomBar

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }

            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.YOUTUBE -> navController.navigateToYoutube(topLevelNavOptions)
            TopLevelDestination.PLAYLIST -> navController.navigateToPlaylist(topLevelNavOptions)
        }
    }
}