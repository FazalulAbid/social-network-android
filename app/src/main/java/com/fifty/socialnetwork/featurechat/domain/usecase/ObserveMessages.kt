package com.fifty.socialnetwork.featurechat.domain.usecase

import com.fifty.socialnetwork.featurechat.data.remote.ChatService
import com.fifty.socialnetwork.featurechat.domain.model.Message
import com.fifty.socialnetwork.featurechat.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ObserveMessages(
    private val chatRepository: ChatRepository
) {

    operator fun invoke(): Flow<Message> {
        return chatRepository.observeMessages()
    }
}