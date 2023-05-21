package com.fifty.socialnetwork.featurechat.presentation.message

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.core.presentation.components.SendTextField
import com.fifty.socialnetwork.core.presentation.components.StandardToolbar
import com.fifty.socialnetwork.core.presentation.ui.theme.ProfilePictureSizeSmall
import com.fifty.socialnetwork.core.presentation.ui.theme.SpaceLarge
import com.fifty.socialnetwork.core.presentation.ui.theme.SpaceMedium
import com.fifty.socialnetwork.core.util.Constants
import com.fifty.socialnetwork.featurechat.domain.model.Message
import com.fifty.socialnetwork.featurechat.presentation.message.components.OwnMessage
import com.fifty.socialnetwork.featurechat.presentation.message.components.RemoteMessage

@OptIn(ExperimentalCoilApi::class)
@Composable
fun MessageScreen(
    chatId: String,
    imageLoader: ImageLoader,
    onNavigateUp: () -> Unit = {},
    onNavigate: (String) -> Unit = {},
    viewModel: MessageViewModel = hiltViewModel()
) {
    val messages = remember {
        listOf(
            Message(
                fromId = "",
                toId = "",
                text = "Hello World, this is testing for long tsext kasjhdfh eiherjelkj sehkghaklesj  klasehjlrtkh aseh kl;ashekl;h khl;k",
                formattedTime = "10:43 AM",
                chatId = "",
            ),
            Message(
                fromId = "",
                toId = "",
                text = "Hello World",
                formattedTime = "10:43 AM",
                chatId = "",
            ),
            Message(
                fromId = "",
                toId = "",
                text = "Hello World",
                formattedTime = "10:43 AM",
                chatId = "",
            ),
            Message(
                fromId = "",
                toId = "",
                text = "Hello World",
                formattedTime = "10:43 AM",
                chatId = "",
            ),
            Message(
                fromId = "",
                toId = "",
                text = "Hello World",
                formattedTime = "10:43 AM",
                chatId = "",
            ),
        )
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            onNavigateUp = onNavigateUp,
            showBackArrow = true,
            title = {
                Image(
                    painter = rememberImagePainter(
                        data = "${Constants.DEBUG_BASE_URL}profile_pictures/4ea2eaa1-7536-4d75-a581-c20e24d52c79.jpg",
                        imageLoader = imageLoader
                    ),
                    contentDescription = null,
                    Modifier
                        .clip(CircleShape)
                        .size(ProfilePictureSizeSmall)
                )
                Spacer(modifier = Modifier.width(SpaceMedium))
                Text(
                    text = "Fazal",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            }
        )
        Column(
            modifier = Modifier.weight(1f)
        ) {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(SpaceMedium)
            ) {
                items(messages) { message ->
                    RemoteMessage(
                        message = message.text,
                        formattedTime = message.formattedTime,
                        color = MaterialTheme.colors.surface,
                        textColor = MaterialTheme.colors.onBackground
                    )
                    Spacer(modifier = Modifier.height(SpaceLarge))
                    OwnMessage(
                        message = message.text,
                        formattedTime = message.formattedTime,
                        color = MaterialTheme.colors.primary,
                        textColor = MaterialTheme.colors.onPrimary
                    )
                    Spacer(modifier = Modifier.height(SpaceLarge))
                }
            }
            SendTextField(
                state = viewModel.messageTextFieldState.value,
                onValueChange = {
                    viewModel.onEvent(MessageEvent.EnteredMessage(it))
                },
                onSend = { viewModel.onEvent(MessageEvent.SendMessage) },
                hint = stringResource(id = R.string.enter_a_message)
            )
        }
    }
}