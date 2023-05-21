package com.fifty.socialnetwork.featurechat.domain.repository

import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.featurechat.domain.model.Chat
import com.fifty.socialnetwork.featurechat.domain.model.Message
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    suspend fun getChatsForUser(): Resource<List<Chat>>

    fun observeChatEvents(): Flow<WebSocket.Event>

    fun observeMessages(): Flow<Message>

    fun sendMessage(toId: String, text: String, chatId: String?)
}