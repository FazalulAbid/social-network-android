package com.fifty.socialnetwork.featurepost.presentation.personlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.PersonRemove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.items
import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.core.domain.models.User
import com.fifty.socialnetwork.core.domain.models.UserItem
import com.fifty.socialnetwork.core.presentation.components.StandardToolbar
import com.fifty.socialnetwork.core.presentation.components.UserProfileItem
import com.fifty.socialnetwork.core.presentation.ui.theme.SpaceLarge
import com.fifty.socialnetwork.core.presentation.ui.theme.SpaceMedium
import com.fifty.socialnetwork.core.presentation.util.UiEvent
import com.fifty.socialnetwork.core.presentation.util.asString
import com.fifty.socialnetwork.core.util.Constants
import com.fifty.socialnetwork.core.util.Screen
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PersonListScreen(
    scaffoldState: ScaffoldState,
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
    viewModel: PersonListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.uiText.asString(context))
                }

                else -> Unit
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        StandardToolbar(
            onNavigateUp = onNavigateUp,
            showBackArrow = true,
            title = {
                Text(
                    text = stringResource(id = R.string.liked_by),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            }
        )
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(SpaceLarge)
            ) {
                items(state.users) { user ->
                    UserProfileItem(
                        user = user,
                        actionIcon = {
                            Icon(
                                imageVector = if (user.isFollowing) {
                                    Icons.Default.PersonRemove
                                } else Icons.Default.PersonAdd,
                                contentDescription = null,
                                tint = MaterialTheme.colors.onBackground
                            )
                        },
                        onItemClick = {
                            onNavigate(Screen.ProfileScreen.route + "?userId=${user.userId}")
                        },
                        onActionItemClick = {
                            viewModel.onEvent(PersonListEvent.ToggleFollowStateForUser(user.userId))
                        },
                        ownUserId = viewModel.ownUserId.value
                    )
                    Spacer(modifier = Modifier.height(SpaceMedium))
                }
            }
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}