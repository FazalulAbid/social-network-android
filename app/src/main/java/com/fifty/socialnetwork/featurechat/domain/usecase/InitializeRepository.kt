package com.fifty.socialnetwork.featurechat.domain.usecase

import com.fifty.socialnetwork.featurechat.data.remote.ChatService
import com.fifty.socialnetwork.featurechat.data.remote.data.WsClientMessage
import com.fifty.socialnetwork.featurechat.domain.repository.ChatRepository

class InitializeRepository(
    private val repository: ChatRepository
) {

    operator fun invoke() {
        repository.initialize()
    }
}