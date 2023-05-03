package com.fifty.socialnetwork.presentation.personlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.domain.model.User
import com.fifty.socialnetwork.presentation.components.StandardToolbar
import com.fifty.socialnetwork.presentation.components.UserProfileItem
import com.fifty.socialnetwork.presentation.ui.theme.SpaceLarge
import com.fifty.socialnetwork.presentation.ui.theme.SpaceMedium

@Composable
fun PersonListScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        StandardToolbar(
            navController = navController,
            showBackArrow = true,
            title = {
                Text(
                    text = stringResource(id = R.string.liked_by),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            }
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(SpaceLarge)
        ) {
            items(10) {
                UserProfileItem(
                    user = User(
                        profilePictureUrl = "",
                        username = "Fazalul Abid",
                        description = "Lorem ipsum dolor sit amet, consec tetur elit. Sedc do eiu" +
                                " consectetur smod tempor consectetur incididunt" +
                                "  ut labore et dolore magna aliqua.",
                        followerCount = 234,
                        followingCount = 221,
                        postCount = 45
                    ),
                    actionIcon = {
                        Icon(
                            imageVector = Icons.Default.PersonAdd,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onBackground
                        )
                    }
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
            }
        }
    }
}