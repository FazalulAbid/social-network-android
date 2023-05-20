package com.fifty.socialnetwork.featurechat.presentation.chat

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.ImageLoader
import com.fifty.socialnetwork.core.presentation.ui.theme.SpaceMedium
import com.fifty.socialnetwork.core.util.Screen
import com.fifty.socialnetwork.featurechat.domain.model.Chat

@Composable
fun ChatScreen(
    imageLoader: ImageLoader,
    onNavigate: (String) -> Unit = {},
    onNavigateUp: () -> Unit = {},
) {
    val chats = remember {
        listOf(
            Chat(
                remoteUsername = "Fazalul Abid",
                remoteUserProfileUrl = "profile_pictures/4ea2eaa1-7536-4d75-a581-c20e24d52c79.jpg",
                lastMessage = "This is the last message of the chat with Abid",
                lastMessageFormattedTimestamp = "09:56 AM"
            ),
            Chat(
                remoteUsername = "Shahala",
                remoteUserProfileUrl = "profile_pictures/4ea2eaa1-7536-4d75-a581-c20e24d52c79.jpg",
                lastMessage = "This is the last message of the chat with Abid",
                lastMessageFormattedTimestamp = "09:56 AM"
            ),
            Chat(
                remoteUsername = "Philipp",
                remoteUserProfileUrl = "profile_pictures/4ea2eaa1-7536-4d75-a581-c20e24d52c79.jpg",
                lastMessage = "This is the last message of the chat with Abid",
                lastMessageFormattedTimestamp = "09:56 AM"
            ),
            Chat(
                remoteUsername = "Philipp",
                remoteUserProfileUrl = "profile_pictures/4ea2eaa1-7536-4d75-a581-c20e24d52c79.jpg",
                lastMessage = "This is the last message of the chat with Abid",
                lastMessageFormattedTimestamp = "09:56 AM"
            ),
            Chat(
                remoteUsername = "Philipp",
                remoteUserProfileUrl = "profile_pictures/4ea2eaa1-7536-4d75-a581-c20e24d52c79.jpg",
                lastMessage = "This is the last message of the chat with Abid",
                lastMessageFormattedTimestamp = "09:56 AM"
            ),
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(SpaceMedium),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(chats) { chat ->
                ChatItem(
                    item = chat,
                    imageLoader = imageLoader,
                    onItemClick = {
                        onNavigate(Screen.MessageScreen.route)
                    }
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
            }
        }
    }
}