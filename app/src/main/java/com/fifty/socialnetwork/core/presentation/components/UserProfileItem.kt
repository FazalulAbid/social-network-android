package com.fifty.socialnetwork.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.core.domain.models.User
import com.fifty.socialnetwork.core.domain.models.UserItem
import com.fifty.socialnetwork.core.presentation.ui.theme.ProfilePictureSizeSmall
import com.fifty.socialnetwork.core.presentation.ui.theme.SpaceMedium
import com.fifty.socialnetwork.core.presentation.ui.theme.SpaceSmall
import com.fifty.socialnetwork.core.util.Constants
import com.fifty.socialnetwork.presentation.ui.theme.*

@OptIn(ExperimentalMaterialApi::class, ExperimentalCoilApi::class)
@Composable
fun UserProfileItem(
    user: UserItem,
    modifier: Modifier = Modifier,
    actionIcon: @Composable () -> Unit = {},
    onItemClick: () -> Unit = {},
    onActionItemClick: () -> Unit = {},
    ownUserId: String = ""
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        onClick = onItemClick,
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = SpaceSmall,
                    horizontal = SpaceMedium
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = rememberImagePainter(
                    data = "${Constants.DEBUG_BASE_URL}${user.profilePictureUrl}",
                    builder = {
                        crossfade(true)
                    }
                ),
                contentDescription = null,
                Modifier
                    .clip(CircleShape)
                    .size(ProfilePictureSizeSmall)
            )
            Spacer(modifier = Modifier.width(SpaceMedium))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = SpaceSmall)
                    .weight(1f)
            ) {
                Text(
                    text = user.username,
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(SpaceSmall))
                Text(
                    text = user.bio,
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }
            if (user.userId != ownUserId) {
                Spacer(modifier = Modifier.width(SpaceSmall))
                IconButton(
                    onClick = onActionItemClick
                ) {
                    actionIcon()
                }
            }
        }
    }
}