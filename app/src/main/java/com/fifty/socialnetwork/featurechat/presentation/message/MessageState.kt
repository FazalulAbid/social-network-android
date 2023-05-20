package com.fifty.socialnetwork.featurechat.presentation.message

import com.fifty.socialnetwork.featurechat.domain.model.Message

data class MessageState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false
)
