package com.fifty.socialnetwork.featurechat.domain.usecase

import com.fifty.socialnetwork.featurechat.data.remote.ChatService
import com.fifty.socialnetwork.featurechat.domain.repository.ChatRepository
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.flow.Flow

class ObserveChatEvents(
    private val chatRepository: ChatRepository
) {

    operator fun invoke(): Flow<WebSocket.Event> {
        return chatRepository.observeChatEvents()
    }
}