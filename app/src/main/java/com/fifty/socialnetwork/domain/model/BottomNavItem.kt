package com.fifty.socialnetwork.domain.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val contentDescription: String? = null,
    val alertCount: Int? = null
)
