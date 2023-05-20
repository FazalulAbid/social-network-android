package com.fifty.socialnetwork.featurechat.domain.model

data class Chat(
    val remoteUsername: String,
    val remoteUserProfileUrl: String,
    val lastMessage: String,
    val lastMessageFormattedTimestamp: String
)
