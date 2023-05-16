package com.fifty.socialnetwork.featureactivity.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.core.domain.models.Activity
import com.fifty.socialnetwork.core.presentation.ui.theme.SpaceSmall
import com.fifty.socialnetwork.domain.util.ActivityType

@Composable
fun ActivityItem(
    activity: Activity,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.onSurface,
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(SpaceSmall),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val fillerText = when (activity.activityType) {
                is ActivityType.CommentedOnPost ->
                    stringResource(R.string.liked)

                is ActivityType.LikedPost ->
                    stringResource(R.string.commented_on)

                is ActivityType.FollowedUser ->
                    stringResource(R.string.followed_you)

                is ActivityType.LikedComment ->
                    stringResource(R.string.liked)
            }
            val actionText = when (activity.activityType) {
                is ActivityType.CommentedOnPost ->
                    stringResource(R.string.your_post)

                is ActivityType.LikedPost ->
                    stringResource(R.string.your_post)

                is ActivityType.FollowedUser -> ""

                is ActivityType.LikedComment ->
                    stringResource(R.string.your_comment)
            }
            Text(
                text = buildAnnotatedString {
                    val boldStyle = SpanStyle(fontWeight = FontWeight.Bold)
                    withStyle(boldStyle) {
                        append(activity.username)
                    }
                    append(" $fillerText ")
                    withStyle(boldStyle) {
                        append(actionText)
                    }
                },
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground
            )
            Text(
                text = activity.formattedTime,
                textAlign = TextAlign.Right,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}