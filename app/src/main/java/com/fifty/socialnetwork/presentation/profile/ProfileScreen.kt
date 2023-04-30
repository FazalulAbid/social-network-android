package com.fifty.socialnetwork.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceIn
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.domain.model.Post
import com.fifty.socialnetwork.domain.model.User
import com.fifty.socialnetwork.presentation.components.Post
import com.fifty.socialnetwork.presentation.profile.components.BannerSection
import com.fifty.socialnetwork.presentation.profile.components.ProfileHeaderSection
import com.fifty.socialnetwork.presentation.ui.theme.ProfilePictureSizeLarge
import com.fifty.socialnetwork.presentation.ui.theme.SpaceMedium
import com.fifty.socialnetwork.presentation.ui.theme.SpaceSmall
import com.fifty.socialnetwork.presentation.util.Screen
import com.fifty.socialnetwork.presentation.util.toPx

@Composable
fun ProfileScreen(
    navController: NavController,
    profilePictureSize: Dp = ProfilePictureSizeLarge,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val lazyListState = rememberLazyListState()
    var toolbarOffsetY = viewModel.toolbarOffsetY.value
    var expandedRatio = viewModel.expandedRatio.value

    val iconHorizontalCenterLength =
        (LocalConfiguration.current.screenWidthDp.dp.toPx() / 4f -
                (profilePictureSize / 4f).toPx() -
                SpaceSmall.toPx()) / 2f
    val iconSizeExpanded = 35.dp
    val toolbarHeightCollapsed = 75.dp
    val imageCollapsedOffsetY = remember {
        (toolbarHeightCollapsed - profilePictureSize / 2f) / 2f
    }
    val iconCollapsedOffsetY = remember {
        (toolbarHeightCollapsed - iconSizeExpanded) / 2f
    }
    val bannerHeight =
        (LocalConfiguration.current.screenWidthDp / 2.5).dp
    val toolbarHeightExpanded = remember {
        bannerHeight + profilePictureSize
    }
    val maxOffset = remember {
        toolbarHeightExpanded - toolbarHeightCollapsed
    }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                if (delta > 0f && lazyListState.firstVisibleItemIndex != 0) {
                    return Offset.Zero
                }
                val newOffset = toolbarOffsetY + delta
                toolbarOffsetY = newOffset.coerceIn(
                    minimumValue = -maxOffset.toPx(),
                    maximumValue = 0f
                )
                expandedRatio = ((toolbarOffsetY + maxOffset.toPx()) / maxOffset.toPx())
                return Offset.Zero
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection),
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = lazyListState
        ) {
            item {
                Spacer(
                    modifier = Modifier
                        .height(toolbarHeightExpanded - profilePictureSize / 2f)
                )
            }
            item {
                ProfileHeaderSection(
                    user = User(
                        profilePictureUrl = "",
                        username = "Fazalul Abid",
                        description = "Lorem ipsum dolor sit amet, consec tetur elit. Sedc do eiu" +
                                " consectetur smod tempor consectetur incididunt" +
                                "  ut labore et dolore magna aliqua.",
                        followerCount = 234,
                        followingCount = 221,
                        postCount = 45
                    )
                )
            }
            item {
                Spacer(
                    modifier = Modifier
                        .height(SpaceMedium)
                        .offset(y = -(profilePictureSize / 2f)),
                )
            }
            items(20) {
                Post(
                    post = Post(
                        username = "Fazalul Abid",
                        imageUrl = "",
                        profilePictureUrl = "",
                        description = "Lorem ipsum dolor sit amet, consec tetur elit. " +
                                "Sedc do eiu consectetur smod tempor consectetur incididunt " +
                                "ut labore et dolore magna aliqua.",
                        likeCount = 12,
                        commentCount = 7
                    ),
                    showProfileImage = false,
                    onPostClick = {
                        navController.navigate(Screen.PostDetailScreen.route)
                    },
                )
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
        ) {
            BannerSection(
                modifier = Modifier
                    .height(
                        (bannerHeight * expandedRatio).coerceIn(
                            minimumValue = toolbarHeightCollapsed,
                            maximumValue = bannerHeight
                        )
                    ),
                leftIconModifier = Modifier
                    .graphicsLayer {
                        translationY = (1f - expandedRatio) *
                                -iconCollapsedOffsetY.toPx()
                        translationX = (1f - expandedRatio) *
                                iconHorizontalCenterLength
                    },
                rightIconModifier = Modifier
                    .graphicsLayer {
                        translationY = (1f - expandedRatio) *
                                -iconCollapsedOffsetY.toPx()
                        translationX = (1f - expandedRatio) *
                                -iconHorizontalCenterLength
                    },
            )
            Image(
                painter = painterResource(id = R.drawable.woman_profile_image),
                contentDescription = stringResource(R.string.profile_image),
                modifier = Modifier
                    .align(CenterHorizontally)
                    .graphicsLayer {
                        translationY = -profilePictureSize.toPx() / 2f -
                                (1f - expandedRatio) * imageCollapsedOffsetY.toPx()
                        transformOrigin = TransformOrigin(
                            pivotFractionX = 0.5f,
                            pivotFractionY = 0f
                        )
                        val scale = 0.5f +
                                expandedRatio * 0.5f
                        scaleX = scale
                        scaleY = scale
                    }

                    .size(profilePictureSize)
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .border(
                        width = 2.dp,
                        color = MaterialTheme.colors.onSurface,
                        shape = CircleShape
                    ),
                contentScale = ContentScale.Crop
            )
        }
    }
}