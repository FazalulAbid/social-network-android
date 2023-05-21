package com.fifty.socialnetwork.featurechat.domain.usecase

import com.fifty.socialnetwork.core.util.Constants
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.featurechat.domain.model.Message
import com.fifty.socialnetwork.featurechat.domain.repository.ChatRepository

class GetMessagesForChat(
    private val repository: ChatRepository
) {

    suspend operator fun invoke(
        chatId: String,
        page: Int,
        pageSize: Int = Constants.DEFAULT_PAGE_SIZE
    ): Resource<List<Message>> {
        return repository.getMessagesForChat(chatId, page, pageSize)
    }
}