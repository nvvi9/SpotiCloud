package com.nvvi9.spoticloud.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nvvi9.spoticloud.ui.icon.Icon
import com.nvvi9.spoticloud.ui.icon.SpotiCloudIcons
import com.nvvi9.spoticloud.ui.theme.SpotiCloudTheme

@Composable
fun RowScope.SpotiCloudNavigationBarItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = SpotiCloudNavigationDefaults.navigationSelectedItemColor,
            unselectedIconColor = SpotiCloudNavigationDefaults.navigationContentColor,
            selectedTextColor = SpotiCloudNavigationDefaults.navigationSelectedItemColor,
            unselectedTextColor = SpotiCloudNavigationDefaults.navigationContentColor,
            indicatorColor = SpotiCloudNavigationDefaults.navigationIndicatorColor
        )
    )
}

@Composable
fun SpotiCloudNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    NavigationBar(
        modifier = modifier,
        contentColor = SpotiCloudNavigationDefaults.navigationContentColor,
        tonalElevation = 0.dp,
        content = content
    )
}

@Composable
fun SpotiCloudNavigationRailItem(
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: @Composable () -> Unit,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true
) {
    NavigationRailItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        modifier = modifier,
        enabled = enabled,
        label = label,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationRailItemDefaults.colors(
            selectedIconColor = SpotiCloudNavigationDefaults.navigationSelectedItemColor,
            unselectedIconColor = SpotiCloudNavigationDefaults.navigationContentColor,
            selectedTextColor = SpotiCloudNavigationDefaults.navigationSelectedItemColor,
            unselectedTextColor = SpotiCloudNavigationDefaults.navigationContentColor,
            indicatorColor = SpotiCloudNavigationDefaults.navigationIndicatorColor
        )
    )
}

@Composable
fun SpotiCloudNavigationRail(
    modifier: Modifier = Modifier,
    header: @Composable (ColumnScope.() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    NavigationRail(
        modifier = modifier,
        containerColor = Color.Transparent,
        contentColor = SpotiCloudNavigationDefaults.navigationContentColor,
        header = header,
        content = content
    )
}

@ThemePreviews
@Composable
fun SpotiCloudNavigationPreview() {
    val items = listOf("YouTube", "Playlist")
    val icons = listOf(SpotiCloudIcons.Playlist, SpotiCloudIcons.Youtube)
    val selectedIcons = listOf(SpotiCloudIcons.Playlist, SpotiCloudIcons.Youtube)

    SpotiCloudTheme {
        SpotiCloudNavigationBar {
            items.forEachIndexed { index, item ->
                SpotiCloudNavigationBarItem(
                    selected = index == 0,
                    onClick = {},
                    icon = {
                        when (val icon = icons[index]) {
                            is Icon.DrawableResourceIcon -> Icon(
                                painter = painterResource(id = icon.id),
                                contentDescription = null
                            )

                            is Icon.ImageVectorIcon -> Icon(
                                imageVector = icon.imageVector,
                                contentDescription = null
                            )
                        }
                    },
                    selectedIcon = {
                        when (val icon = selectedIcons[index]) {
                            is Icon.DrawableResourceIcon -> Icon(
                                painter = painterResource(id = icon.id),
                                contentDescription = null
                            )

                            is Icon.ImageVectorIcon -> Icon(
                                imageVector = icon.imageVector,
                                contentDescription = null
                            )
                        }
                    },
                    label = { Text(text = item) }
                )
            }
        }
    }
}

object SpotiCloudNavigationDefaults {

    val navigationContentColor @Composable get() = MaterialTheme.colorScheme.onSurfaceVariant

    val navigationSelectedItemColor @Composable get() = MaterialTheme.colorScheme.onPrimaryContainer

    val navigationIndicatorColor @Composable get() = MaterialTheme.colorScheme.primaryContainer
}