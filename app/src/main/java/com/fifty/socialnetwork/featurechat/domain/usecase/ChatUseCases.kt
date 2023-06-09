package com.fifty.socialnetwork.featurechat.domain.usecase

import com.tinder.scarlet.ws.Send

data class ChatUseCases(
    val sendMessage: SendMessage,
    val observeChatEvents: ObserveChatEvents,
    val observeMessages: ObserveMessages,
    val getChatsForUser: GetChatsForUser,
    val getMessagesForChat: GetMessagesForChat,
    val initializeRepository: InitializeRepository
)