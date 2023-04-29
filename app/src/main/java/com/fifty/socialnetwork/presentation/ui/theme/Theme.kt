package com.fifty.socialnetwork.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = RedAccent,
    background = DarkGray,
    onBackground = TextWhite,
    onPrimary = Color.White,
    surface = MediumGray,
    onSurface = LightGray
)

@Composable
fun SocialNetworkTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = DarkColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}