package com.fifty.socialnetwork.presentation.profile.components

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.domain.model.User
import com.fifty.socialnetwork.presentation.ui.theme.SpaceLarge
import com.fifty.socialnetwork.presentation.ui.theme.SpaceMedium

@Composable
fun ProfileStats(
    user: User,
    modifier: Modifier = Modifier,
    isOwnProfile: Boolean = true,
    isFollowing: Boolean = false,
    onFollowClick: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileNumber(
            number = user.followerCount,
            text = stringResource(R.string.followers)
        )
        Spacer(modifier = Modifier.width(SpaceLarge))
        ProfileNumber(
            number = user.followingCount,
            text = stringResource(R.string.following)
        )
        Spacer(modifier = Modifier.width(SpaceLarge))
        ProfileNumber(
            number = user.postCount,
            text = stringResource(R.string.posts)
        )
        if (isOwnProfile) {
            Spacer(modifier = Modifier.width(SpaceLarge))
            Button(
                onClick = onFollowClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = if (isFollowing) {
                        Color.Yellow
                    } else MaterialTheme.colors.primary
                )
            ) {
                Text(
                    text = if (isFollowing) {
                        stringResource(id = R.string.unfollow)
                    } else stringResource(id = R.string.follow),
                    color = if (isFollowing) Color.Black else Color.White
                )
            }
        }
    }
}