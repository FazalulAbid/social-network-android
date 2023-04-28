package com.fifty.socialnetwork.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fifty.socialnetwork.presentation.components.Post
import com.fifty.socialnetwork.presentation.components.StandardScaffold

@Composable
fun MainFeedScreen(
    navController: NavController
) {
    StandardScaffold(
        navController = navController,
        modifier = Modifier.fillMaxSize()
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
}