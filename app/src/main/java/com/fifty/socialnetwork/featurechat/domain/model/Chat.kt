package com.fifty.socialnetwork.featurechat.domain.model

data class Chat(
    val chatId: String,
    val remoteUserId: String,
    val remoteUsername: String,
    val remoteUserProfilePictureUrl: String,
    val lastMessage: String,
    val timestamp: Long
)