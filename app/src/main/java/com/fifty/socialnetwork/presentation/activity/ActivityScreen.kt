package com.fifty.socialnetwork.presentation.activity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.domain.model.Activity
import com.fifty.socialnetwork.domain.model.Comment
import com.fifty.socialnetwork.domain.util.ActivityAction
import com.fifty.socialnetwork.domain.util.DateFormatUtil
import com.fifty.socialnetwork.presentation.components.ActionRow
import com.fifty.socialnetwork.presentation.components.StandardScaffold
import com.fifty.socialnetwork.presentation.components.StandardToolbar
import com.fifty.socialnetwork.presentation.postdetail.Comment
import com.fifty.socialnetwork.presentation.ui.theme.*
import com.fifty.socialnetwork.presentation.util.Screen
import java.sql.Timestamp
import java.util.Random

@Composable
fun ActivityScreen(
    navController: NavController,
    viewModel: ActivityViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            navController = navController,
            title = {
                Text(
                    text = stringResource(R.string.your_activity),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = false,
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            contentPadding = PaddingValues(SpaceMedium)
        ) {
            items(20) {
                ActivityItem(
                    activity = Activity(
                        username = "Fazalul Abid",
                        actionType = if (kotlin.random.Random.nextInt(2) == 0)
                            ActivityAction.LikedPost
                        else ActivityAction.CommentedOnPost,
                        formattedTime = DateFormatUtil.timestampToFormattedString(
                            timestamp = System.currentTimeMillis(),
                            pattern = "MMM dd, HH:mm"
                        )
                    ),
                    modifier = Modifier
                        .padding(bottom = SpaceMedium)
                )
            }
        }
    }
}