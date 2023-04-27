package com.fifty.socialnetwork.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.fifty.socialnetwork.presentation.components.Post

@Composable
fun MainFeedScreen(
    navController: NavController
) {
    Post(
        post = com.fifty.socialnetwork.domain.model.Post(
            username = "Fazalul Abid",
            imageUrl = "",
            profilePictureUrl = "",
            description = "Lorem ipsum dolor sit amet, consec tetur elit. Sedc do eiu consectetur smod tempor consectetur incididunt ut labore et dolore magna aliqua.",
            likeCount = 12,
            commentCount = 7
        )
    )
}