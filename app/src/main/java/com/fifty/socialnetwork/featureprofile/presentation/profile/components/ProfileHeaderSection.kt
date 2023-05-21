package com.fifty.socialnetwork.featureprofile.presentation.profile.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Message
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.core.domain.models.User
import com.fifty.socialnetwork.core.presentation.ui.theme.SpaceLarge
import com.fifty.socialnetwork.core.presentation.ui.theme.SpaceMedium
import com.fifty.socialnetwork.core.presentation.ui.theme.SpaceSmall

@Composable
fun ProfileHeaderSection(
    user: User,
    modifier: Modifier = Modifier,
    isFollowing: Boolean = true,
    isOwnProfile: Boolean = true,
    onEditClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    onMessageClick: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(SpaceMedium))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .offset(
                    x =
                    if (isOwnProfile) ((SpaceSmall + 30.dp)) / 2f else 0.dp
                )

        ) {
            Text(
                text = user.username,
                style = MaterialTheme.typography.h1.copy(
                    fontSize = 24.sp
                ),
                textAlign = TextAlign.Center
            )
            if (!isOwnProfile) {
                Spacer(modifier = Modifier.width(SpaceSmall))
                IconButton(
                    onClick = onMessageClick,
                    modifier = Modifier.size(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Message,
                        contentDescription = stringResource(R.string.message)
                    )
                }
            }
            if (isOwnProfile) {
                Spacer(modifier = Modifier.width(SpaceSmall))
                Spacer(modifier = Modifier.width(SpaceSmall))
                IconButton(
                    onClick = onEditClick,
                    modifier = Modifier.size(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = stringResource(R.string.edit)
                    )
                }
                IconButton(
                    onClick = onLogoutClick,
                    modifier = Modifier.size(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Logout,
                        contentDescription = stringResource(R.string.logout)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(SpaceLarge))
        if (user.description.isNotBlank()) {
            Text(
                text = user.description,
                style = MaterialTheme.typography.body2,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(SpaceLarge))
        }
        ProfileStats(
            user = user,
            isOwnProfile = isOwnProfile,
            isFollowing = isFollowing
        )
    }
}