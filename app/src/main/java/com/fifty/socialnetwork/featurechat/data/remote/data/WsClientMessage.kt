package com.fifty.socialnetwork.featurechat.data.remote.data


data class WsClientMessage(
    val toId: String,
    val text: String,
    val chatId: String?
)
