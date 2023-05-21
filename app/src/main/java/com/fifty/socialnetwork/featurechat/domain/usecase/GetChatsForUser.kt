package com.fifty.socialnetwork.featurechat.domain.usecase

import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.featurechat.domain.model.Chat
import com.fifty.socialnetwork.featurechat.domain.repository.ChatRepository

class GetChatsForUser(
    private val repository: ChatRepository
) {

    suspend operator fun invoke(): Resource<List<Chat>> {
        return repository.getChatsForUser()
    }
}