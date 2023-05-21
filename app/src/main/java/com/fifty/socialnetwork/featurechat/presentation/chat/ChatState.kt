package com.fifty.socialnetwork.featurechat.presentation.chat

import com.fifty.socialnetwork.featurechat.domain.model.Chat

data class ChatState(
    val chats: List<Chat> = emptyList(),
    val isLoading: Boolean = false
)
