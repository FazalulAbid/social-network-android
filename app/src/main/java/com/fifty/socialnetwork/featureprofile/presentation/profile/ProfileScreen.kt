package com.fifty.socialnetwork.featureprofile.presentation.profile

import android.util.Base64
import  androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceIn
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.core.domain.models.User
import com.fifty.socialnetwork.core.presentation.components.Post
import com.fifty.socialnetwork.featureprofile.presentation.profile.components.BannerSection
import com.fifty.socialnetwork.featureprofile.presentation.profile.components.ProfileHeaderSection
import com.fifty.socialnetwork.core.presentation.ui.theme.ProfilePictureSizeLarge
import com.fifty.socialnetwork.core.presentation.ui.theme.SpaceMedium
import com.fifty.socialnetwork.core.presentation.ui.theme.SpaceSmall
import com.fifty.socialnetwork.core.presentation.util.UiEvent
import com.fifty.socialnetwork.core.presentation.util.asString
import com.fifty.socialnetwork.core.util.Constants
import com.fifty.socialnetwork.core.util.Screen
import com.fifty.socialnetwork.core.util.openUrlInBrowser
import com.fifty.socialnetwork.core.util.sendSharePostIntent
import com.fifty.socialnetwork.core.util.toPx
import com.fifty.socialnetwork.featurepost.presentation.personlist.PostEvent
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ProfileScreen(
    scaffoldState: ScaffoldState,
    imageLoader: ImageLoader,
    userId: String? = null,
    onNavigate: (String) -> Unit = {},
    onLogout: () -> Unit = {},
    profilePictureSize: Dp = ProfilePictureSizeLarge,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val pagingState = viewModel.pagingState.value
    val lazyListState = rememberLazyListState()
    val toolbarState = viewModel.toolbarState.value

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
    val bannerHeight = (LocalConfiguration.current.screenWidthDp / 2.5f).dp
    val toolbarHeightExpanded = remember {
        bannerHeight + profilePictureSize
    }
    val maxOffset = remember {
        toolbarHeightExpanded - toolbarHeightCollapsed
    }

    val state = viewModel.state.value
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val shouldNotScroll = delta > 0f && lazyListState.firstVisibleItemIndex != 0 ||
                        viewModel.pagingState.value.items.isEmpty()
                if (shouldNotScroll) {
                    return Offset.Zero
                }
                val newOffset = viewModel.toolbarState.value.toolbarOffsetY + delta
                viewModel.setToolbarOffsetY(
                    newOffset.coerceIn(
                        minimumValue = -maxOffset.toPx(),
                        maximumValue = 0f
                    )
                )
                viewModel.setExpandedRatio((viewModel.toolbarState.value.toolbarOffsetY + maxOffset.toPx()) / maxOffset.toPx())
                return Offset.Zero
            }

        }
    }

    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.getProfile(userId)
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }

                is PostEvent.OnLiked -> {

                }

                is UiEvent.Navigate -> {

                }

                is UiEvent.NavigateUp -> {

                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = lazyListState
        ) {
            item {
                Spacer(
                    modifier = Modifier.height(
                        toolbarHeightExpanded - profilePictureSize / 2f
                    )
                )
            }
            item {
                state.profile?.let { profile ->
                    ProfileHeaderSection(
                        user = User(
                            userId = profile.userId,
                            profilePictureUrl = "${Constants.DEBUG_BASE_URL}${profile.profilePictureUrl}",
                            username = profile.username,
                            description = profile.bio,
                            followerCount = profile.followerCount,
                            followingCount = profile.followingCount,
                            postCount = profile.postCount
                        ),
                        isFollowing = profile.isFollowing,
                        isOwnProfile = profile.isOwnProfile,
                        onLogoutClick = {
                            viewModel.onEvent(ProfileEvent.ShowLogoutDialog)
                        },
                        onEditClick = {
                            onNavigate(Screen.EditProfileScreen.route + "/${profile.userId}")
                        },
                        onMessageClick = {
                            val encodedProfilePictureUrl = Base64.encodeToString(
                                profile.profilePictureUrl.encodeToByteArray(), 0
                            )
                            onNavigate(
                                Screen.MessageScreen.route + "/${profile.userId}/${profile.username}/${encodedProfilePictureUrl}"
                            )
                        }
                    )
                }
            }
            items(pagingState.items.size) { i ->
                val post = pagingState.items[i]
                if (i >= pagingState.items.size - 1 && !pagingState.endReached && !pagingState.isLoading) {
                    viewModel.loadNextPosts()
                }

                Post(
                    post = post,
                    imageLoader = imageLoader,
                    showProfileImage = false,
                    onPostClick = {
                        onNavigate(Screen.PostDetailScreen.route + "/${post.id}")
                    },
                    onCommentClick = {
                        onNavigate(Screen.PostDetailScreen.route + "/${post.id}?shouldShowKeyboard=true")
                    },
                    onLikeClick = {
                        viewModel.onEvent(ProfileEvent.LikePost(post.id))
                    },
                    onShareClick = {
                        context.sendSharePostIntent(post.id)
                    },
                    onDeleteClick = {
                        viewModel.onEvent(ProfileEvent.DeletePost(post))
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(90.dp))
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
        ) {
            state.profile?.let { profile ->
                BannerSection(
                    modifier = Modifier
                        .height(
                            (bannerHeight * toolbarState.expandedRatio).coerceIn(
                                minimumValue = toolbarHeightCollapsed,
                                maximumValue = bannerHeight
                            )
                        ),
                    leftIconModifier = Modifier
                        .graphicsLayer {
                            translationY = (1f - toolbarState.expandedRatio) *
                                    -iconCollapsedOffsetY.toPx()
                            translationX = (1f - toolbarState.expandedRatio) *
                                    iconHorizontalCenterLength
                        },
                    rightIconModifier = Modifier
                        .graphicsLayer {
                            translationY = (1f - toolbarState.expandedRatio) *
                                    -iconCollapsedOffsetY.toPx()
                            translationX = (1f - toolbarState.expandedRatio) *
                                    -iconHorizontalCenterLength
                        },
                    bannerUrl = "${Constants.DEBUG_BASE_URL}${profile.bannerUrl}",
                    imageLoader = imageLoader,
                    topSkills = profile.topSkills,
                    shouldShowGitHub = !profile.gitHubUrl.isNullOrBlank(),
                    shouldShowInstagram = !profile.instagramUrl.isNullOrBlank(),
                    shouldShowLinkedIn = !profile.linkedInUrl.isNullOrBlank(),
                    onGitHubClick = {
                        context.openUrlInBrowser(profile.gitHubUrl ?: return@BannerSection)
                    },
                    onInstagramClick = {
                        context.openUrlInBrowser(profile.instagramUrl ?: return@BannerSection)
                    },
                    onLinkedInClick = {
                        context.openUrlInBrowser(profile.linkedInUrl ?: return@BannerSection)
                    }
                )
                Image(
                    painter = rememberImagePainter(
                        data = "${Constants.DEBUG_BASE_URL}${profile.profilePictureUrl}",
                        imageLoader = imageLoader
                    ),
                    contentDescription = stringResource(id = R.string.profile_image),
                    modifier = Modifier
                        .align(CenterHorizontally)
                        .graphicsLayer {
                            translationY = -profilePictureSize.toPx() / 2f -
                                    (1f - toolbarState.expandedRatio) * imageCollapsedOffsetY.toPx()
                            transformOrigin = TransformOrigin(
                                pivotFractionX = 0.5f,
                                pivotFractionY = 0f
                            )
                            val scale = 0.5f + toolbarState.expandedRatio * 0.5f
                            scaleX = scale
                            scaleY = scale
                        }
                        .size(profilePictureSize)
                        .clip(CircleShape)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colors.onSurface,
                            shape = CircleShape
                        ),
                    contentScale = ContentScale.Crop
                )
            }
        }
        if (state.isLogoutDialogVisible) {
            Dialog(onDismissRequest = {
                viewModel.onEvent(ProfileEvent.DismissLogoutDialog)
            }) {
                Column(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colors.surface,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(SpaceMedium)
                ) {
                    Text(text = stringResource(id = R.string.do_you_want_to_logout))
                    Spacer(modifier = Modifier.height(SpaceMedium))
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(
                            text = stringResource(id = R.string.no).uppercase(),
                            color = MaterialTheme.colors.onBackground,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                viewModel.onEvent(ProfileEvent.DismissLogoutDialog)
                            }
                        )
                        Spacer(modifier = Modifier.width(SpaceMedium))
                        Text(
                            text = stringResource(id = R.string.yes).uppercase(),
                            color = MaterialTheme.colors.primary,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.clickable {
                                viewModel.onEvent(ProfileEvent.Logout)
                                viewModel.onEvent(ProfileEvent.DismissLogoutDialog)
                                onLogout()
                            }
                        )
                    }
                }
            }
        }
    }
}