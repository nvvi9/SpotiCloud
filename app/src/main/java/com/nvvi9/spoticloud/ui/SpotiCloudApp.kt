package com.nvvi9.spoticloud.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.nvvi9.spoticloud.navigation.SpotiCloudNavHost
import com.nvvi9.spoticloud.navigation.TopLevelDestination
import com.nvvi9.spoticloud.ui.components.SpotiCloudBackground
import com.nvvi9.spoticloud.ui.components.SpotiCloudGradientBackground
import com.nvvi9.spoticloud.ui.components.SpotiCloudNavigationBar
import com.nvvi9.spoticloud.ui.components.SpotiCloudNavigationBarItem
import com.nvvi9.spoticloud.ui.components.SpotiCloudNavigationRail
import com.nvvi9.spoticloud.ui.components.SpotiCloudNavigationRailItem
import com.nvvi9.spoticloud.ui.components.SpotiCloudTopAppBar
import com.nvvi9.spoticloud.ui.icon.asComposeIcon
import com.nvvi9.spoticloud.ui.theme.LocalGradientColors

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SpotiCloudApp(
    windowSizeClass: WindowSizeClass,
    appState: SpotiCloudAppState = rememberSpotiCloudState(windowSizeClass = windowSizeClass)
) {
    SpotiCloudBackground {
        SpotiCloudGradientBackground(gradientColors = LocalGradientColors.current) {
            Scaffold(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,
                contentWindowInsets = WindowInsets(0, 0, 0, 0),
                bottomBar = {
                    if (appState.shouldShowBottomBar) {
                        SpotiCloudBottomBar(
                            destinations = appState.topLevelDestinations,
                            onNavigationToDestination = appState::navigateToTopLevelDestination,
                            currentDestination = appState.currentDestination,
                            modifier = Modifier
                        )
                    }
                }
            ) { padding ->
                Row(
                    Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .consumeWindowInsets(padding)
                        .windowInsetsPadding(
                            WindowInsets.safeDrawing.only(
                                WindowInsetsSides.Horizontal
                            )
                        )
                ) {
                    if (appState.shouldShowNavRail) {
                        SpotiCloudNavRail(
                            destinations = appState.topLevelDestinations,
                            onNavigateToDestination = appState::navigateToTopLevelDestination,
                            currentDestination = appState.currentDestination,
                            modifier = Modifier.safeDrawingPadding()
                        )
                    }

                    Column(Modifier.fillMaxSize()) {
                        appState.currentTopLevelDestination?.let { destination ->
                            SpotiCloudTopAppBar(titleRes = destination.titleTextId)
                        }

                        SpotiCloudNavHost(appState = appState)
                    }
                }
            }
        }
    }
}

@Composable
private fun SpotiCloudNavRail(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {
    SpotiCloudNavigationRail(modifier = modifier) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)

            SpotiCloudNavigationRailItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = { destination.unselectedIcon.asComposeIcon() },
                selectedIcon = { destination.selectedIcon.asComposeIcon() },
                label = { Text(text = stringResource(id = destination.iconTextId)) },
                modifier = Modifier
            )
        }
    }
}

@Composable
private fun SpotiCloudBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigationToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {
    SpotiCloudNavigationBar(modifier = modifier) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            SpotiCloudNavigationBarItem(
                selected = selected,
                onClick = { onNavigationToDestination(destination) },
                icon = destination.unselectedIcon.asComposeIcon(),
                selectedIcon = destination.selectedIcon.asComposeIcon(),
                label = { Text(stringResource(id = destination.iconTextId)) },
                modifier = Modifier
            )
        }
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: TopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false