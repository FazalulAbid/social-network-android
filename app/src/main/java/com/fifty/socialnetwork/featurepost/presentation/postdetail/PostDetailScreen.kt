package com.fifty.socialnetwork.featurepost.presentation.postdetail

import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getSystemService
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.core.presentation.components.ActionRow
import com.fifty.socialnetwork.core.presentation.components.SendTextField
import com.fifty.socialnetwork.core.presentation.components.StandardTextField
import com.fifty.socialnetwork.core.presentation.components.StandardToolbar
import com.fifty.socialnetwork.core.presentation.ui.theme.*
import com.fifty.socialnetwork.core.presentation.util.UiEvent
import com.fifty.socialnetwork.core.presentation.util.asString
import com.fifty.socialnetwork.core.presentation.util.showKeyboard
import com.fifty.socialnetwork.core.util.Constants
import com.fifty.socialnetwork.core.util.Screen
import com.fifty.socialnetwork.core.util.sendSharePostIntent
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PostDetailScreen(
    scaffoldState: ScaffoldState,
    imageLoader: ImageLoader,
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    viewModel: PostDetailViewModel = hiltViewModel(),
    shouldShowKeyboard: Boolean = false
) {
    val state = viewModel.state.value
    val commentTextFieldState = viewModel.commentTextFieldState.value

    val focusRequester = remember {
        FocusRequester()
    }

    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        if (shouldShowKeyboard) {
            context.showKeyboard()
            focusRequester.requestFocus()
        }
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }

                else -> Unit
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            onNavigateUp = onNavigateUp,
            title = {
                Text(
                    text = stringResource(R.string.your_feed),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = true,
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .background(MaterialTheme.colors.surface)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.background)
                ) {
                    Spacer(modifier = Modifier.height(SpaceLarge))
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .offset(y = ProfilePictureSizeMedium / 2f)
                                .background(MediumGray)
                        ) {
                            state.post?.let { post ->
                                Image(
                                    painter = rememberImagePainter(
                                        data = "${Constants.DEBUG_BASE_URL}${state.post.imageUrl}",
                                        imageLoader = imageLoader
                                    ),
                                    contentScale = ContentScale.Crop,
                                    contentDescription = "Post image",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(16f / 9f)
                                )
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(SpaceLarge)
                                ) {
                                    ActionRow(
                                        username = state.post.username,
                                        modifier = Modifier.fillMaxWidth(),
                                        onLikeClick = {
                                            viewModel.onEvent(PostDetailEvent.LikePost)
                                        },
                                        onCommentClick = {
                                            context.showKeyboard()
                                            focusRequester.requestFocus()
                                        },
                                        onShareClick = {
                                            context.sendSharePostIntent(post.id)
                                        },
                                        onUsernameClick = {
                                            onNavigate(Screen.ProfileScreen.route + "?userId=${post.userId}")
                                        },
                                        isLiked = state.post.isLiked
                                    )
                                    Spacer(modifier = Modifier.height(SpaceMedium))
                                    Text(
                                        text = state.post.description,
                                        style = MaterialTheme.typography.body2
                                    )
                                    Spacer(modifier = Modifier.height(SpaceMedium))
                                    Text(
                                        text = stringResource(
                                            R.string.x_likes, state.post.likeCount
                                        ),
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.body2,
                                        modifier = Modifier.clickable {
                                            onNavigate(Screen.PersonListScreen.route + "/${post.id}")
                                        }
                                    )
                                }
                            }
                        }
                        Image(
                            painter = rememberImagePainter(
                                data = "${Constants.DEBUG_BASE_URL}${state.post?.profilePictureUrl}",
                                imageLoader = imageLoader
                            ),
                            contentDescription = "Profile picture",
                            modifier = Modifier
                                .size(ProfilePictureSizeMedium)
                                .clip(CircleShape)
                                .align(Alignment.TopCenter)
                        )
                        if (state.isLoadingPost) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(SpaceLarge))
            }
            items(state.comments) { comment ->
                Comment(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = SpaceLarge,
                            vertical = SpaceSmall
                        ),
                    imageLoader = imageLoader,
                    comment = comment,
                    onLikeClick = {
                        viewModel.onEvent(PostDetailEvent.LikeComment(comment.id))
                    },
                    onLikedByClick = {
                        onNavigate(Screen.PersonListScreen.route + "/${comment.id}")
                    }
                )
            }
        }
        SendTextField(
            state = viewModel.commentTextFieldState.value,
            onValueChange = {
                viewModel.onEvent(PostDetailEvent.EnteredComment(it))
            },
            onSend = { viewModel.onEvent(PostDetailEvent.Comment) },
            hint = stringResource(id = R.string.enter_a_comment),
            isLoading = viewModel.commentState.value.isLoading,
            focusRequester = focusRequester
        )
    }
}