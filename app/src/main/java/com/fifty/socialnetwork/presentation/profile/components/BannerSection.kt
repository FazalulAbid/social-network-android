package com.fifty.socialnetwork.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.presentation.ui.theme.SpaceMedium
import com.fifty.socialnetwork.presentation.ui.theme.SpaceSmall
import com.fifty.socialnetwork.presentation.util.toPx

@Composable
fun BannerSection(
    modifier: Modifier = Modifier,
    iconSize: Dp = 35.dp,
    iconModifier: Modifier = Modifier,

    onGitHubClick: () -> Unit = {},
    onInstagramClick: () -> Unit = {},
    onLinkedInClick: () -> Unit = {}
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = R.drawable.cover_image),
            contentDescription = stringResource(R.string.banner_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = constraints.maxHeight - iconSize.toPx() * 2f
                    )
                )
        )
        Row(
            modifier = iconModifier
                .height(iconSize)
                .align(Alignment.BottomStart)
                .padding(SpaceSmall)
        ) {
            Spacer(modifier = Modifier.width(SpaceSmall))
            Image(
                painter = painterResource(id = R.drawable.ic_javascript_logo),
                contentDescription = "Javascript",
                modifier = Modifier.height(iconSize)
            )
            Spacer(modifier = Modifier.width(SpaceMedium))
            Image(
                painter = painterResource(id = R.drawable.ic_csharp_logo),
                contentDescription = "C#",
                modifier = Modifier.height(iconSize)
            )
            Spacer(modifier = Modifier.width(SpaceMedium))
            Image(
                painter = painterResource(id = R.drawable.ic_kotlin_logo),
                contentDescription = "Kotlin",
                modifier = Modifier.height(iconSize)
            )
        }
        Row(
            modifier = iconModifier
                .height(iconSize)
                .align(Alignment.BottomEnd)
                .padding(SpaceSmall)
        ) {
            IconButton(
                onClick = onGitHubClick,
                modifier = Modifier.size(iconSize)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_github_logo),
                    contentDescription = "Github",
                    tint = Color.White
                )
            }
            IconButton(
                onClick = onInstagramClick,
                modifier = Modifier.size(iconSize)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_instagram_logo),
                    contentDescription = "Instagram",
                    tint = Color.White
                )
            }
            IconButton(
                onClick = onLinkedInClick,
                modifier = Modifier.size(iconSize)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_linkedin_logo),
                    contentDescription = "LinkedIn",
                    tint = Color.White
                )
            }
        }
    }
}