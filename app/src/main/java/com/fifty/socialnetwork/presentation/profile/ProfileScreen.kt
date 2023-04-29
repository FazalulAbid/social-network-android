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
import com.fifty.socialnetwork.domain.util.ActivityAction
import com.fifty.socialnetwork.domain.util.DateFormatUtil
import com.fifty.socialnetwork.presentation.activity.ActivityItem
import com.fifty.socialnetwork.presentation.components.StandardScaffold
import com.fifty.socialnetwork.presentation.components.StandardToolbar
import com.fifty.socialnetwork.presentation.ui.theme.SpaceMedium
import kotlin.random.Random

@Composable
fun ProfileScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize()
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
                .fillMaxSize(),
            contentPadding = PaddingValues(SpaceMedium)
        ) {
            item {

            }
            items(20) {

            }
        }
    }
}