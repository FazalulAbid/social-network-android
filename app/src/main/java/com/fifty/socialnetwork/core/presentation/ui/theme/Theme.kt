package com.fifty.socialnetwork.presentation.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.fifty.socialnetwork.core.presentation.ui.theme.*

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