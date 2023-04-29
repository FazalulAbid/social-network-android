package com.fifty.socialnetwork.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.domain.model.Activity
import com.fifty.socialnetwork.domain.model.Post
import com.fifty.socialnetwork.domain.model.User
import com.fifty.socialnetwork.domain.util.ActivityAction
import com.fifty.socialnetwork.domain.util.DateFormatUtil
import com.fifty.socialnetwork.presentation.activity.ActivityItem
import com.fifty.socialnetwork.presentation.components.Post
import com.fifty.socialnetwork.presentation.components.StandardScaffold
import com.fifty.socialnetwork.presentation.components.StandardToolbar
import com.fifty.socialnetwork.presentation.profile.components.BannerSection
import com.fifty.socialnetwork.presentation.profile.components.ProfileHeaderSection
import com.fifty.socialnetwork.presentation.ui.theme.ProfilePictureSizeLarge
import com.fifty.socialnetwork.presentation.ui.theme.SpaceMedium
import com.fifty.socialnetwork.presentation.util.Screen
import kotlin.random.Random

@Composable
fun ProfileScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        StandardToolbar(
            navController = navController,
            title = {
                Text(
                    text = stringResource(R.string.your_profile),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = false,
            navActions = {

            }
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                BannerSection(
                    modifier = Modifier
                        .aspectRatio(2.5f)
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
                        .offset(y = -(ProfilePictureSizeLarge / 2f)),
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
                    modifier = Modifier
                        .offset(y = -(ProfilePictureSizeLarge / 2f)),

                    )
            }
        }
    }
}