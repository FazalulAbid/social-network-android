package com.fifty.socialnetwork.featurechat.data.repository

import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.core.util.UiText
import com.fifty.socialnetwork.featurechat.data.remote.ChatApi
import com.fifty.socialnetwork.featurechat.data.remote.ChatService
import com.fifty.socialnetwork.featurechat.data.remote.data.WsClientMessage
import com.fifty.socialnetwork.featurechat.domain.model.Chat
import com.fifty.socialnetwork.featurechat.domain.model.Message
import com.fifty.socialnetwork.featurechat.domain.repository.ChatRepository
import com.tinder.scarlet.WebSocket
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException

class ChatRepositoryImpl(
    private val chatService: ChatService,
    private val chatApi: ChatApi
) : ChatRepository {

    override suspend fun getChatsForUser(): Resource<List<Chat>> {
        return try {
            val chats = chatApi.getChatsForUser()
                .mapNotNull { it.toChat() }
            Resource.Success(data = chats)
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_could_not_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }

    override suspend fun getMessagesForChat(
        chatId: String,
        page: Int,
        pageSize: Int
    ): Resource<List<Message>> {
        return try {
            val messages = chatApi.getMessagesForChat(chatId, page, pageSize)
                .map { it.toMessage() }
            Resource.Success(data = messages)
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_could_not_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }

    override fun observeChatEvents(): Flow<WebSocket.Event> {
        return chatService.observeEvents()
    }

    override fun observeMessages(): Flow<Message> {
        return chatService.observeMessages()
            .map { it.toMessage() }
    }

    override fun sendMessage(toId: String, text: String, chatId: String?) {
        chatService.sendMessage(
            WsClientMessage(
                toId = toId,
                text = text,
                chatId = chatId
            )
        )
    }
}