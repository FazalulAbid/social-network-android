package com.fifty.socialnetwork.featurechat.domain.usecase

import com.fifty.socialnetwork.featurechat.data.remote.ChatService
import com.fifty.socialnetwork.featurechat.data.remote.data.WsClientMessage
import com.fifty.socialnetwork.featurechat.domain.repository.ChatRepository

class SendMessage(
    private val chatRepository: ChatRepository
) {

    operator fun invoke(toId: String, text: String, chatId: String?) {
        if (text.isBlank()) {
            return
        }
        chatRepository.sendMessage(toId, text, chatId)
    }
}