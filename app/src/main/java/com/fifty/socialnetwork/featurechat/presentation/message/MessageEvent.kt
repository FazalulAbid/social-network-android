package com.fifty.socialnetwork.featurechat.presentation.message

sealed class MessageEvent {
    object SendMessage : MessageEvent()
    data class EnteredMessage(val message: String) : MessageEvent()
}
