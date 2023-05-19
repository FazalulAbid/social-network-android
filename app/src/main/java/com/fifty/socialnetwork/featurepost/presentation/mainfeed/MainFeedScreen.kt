package com.fifty.socialnetwork.featurepost.presentation.mainfeed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.core.presentation.components.Post
import com.fifty.socialnetwork.core.presentation.components.StandardToolbar
import com.fifty.socialnetwork.core.util.Screen
import com.fifty.socialnetwork.featurepost.presentation.mainfeed.MainFeedEvent
import com.fifty.socialnetwork.featurepost.presentation.mainfeed.MainFeedViewModel
import com.fifty.socialnetwork.featurepost.presentation.personlist.PostEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun MainFeedScreen(
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    scaffoldState: ScaffoldState,
    viewModel: MainFeedViewModel = hiltViewModel()
) {
    val pagingState = viewModel.pagingState.value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is PostEvent.OnLiked -> {

                }
            }
        }
    }
    Column(
        modifier = Modifier.fillMaxWidth()
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
            showBackArrow = false,
            navActions = {
                IconButton(
                    onClick = {
                        onNavigate(Screen.SearchScreen.route)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "",
                        tint = MaterialTheme.colors.onBackground
                    )
                }
            }
        )
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                items(pagingState.items.size) { i ->
                    val post = pagingState.items[i]
                    if (i >= pagingState.items.size - 1 && !pagingState.endReached && !pagingState.isLoading) {
                        viewModel.loadNextPosts()
                    }
                    Post(
                        post = post,
                        onPostClick = {
                            onNavigate(Screen.PostDetailScreen.route + "/${post.id}")
                        },
                        onLikeClick = {
                            viewModel.onEvent(MainFeedEvent.LikedPost(post.id))
                        }
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(90.dp))
                }
            }
            if (pagingState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Center)
                )
            }
        }
    }
}