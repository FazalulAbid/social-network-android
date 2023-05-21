package com.fifty.socialnetwork.featurechat.data.remote

import com.fifty.socialnetwork.core.util.Constants
import com.fifty.socialnetwork.featurechat.data.remote.data.ChatDto
import com.fifty.socialnetwork.featurechat.data.remote.data.MessageDto
import com.fifty.socialnetwork.featurechat.domain.model.Message
import retrofit2.http.GET
import retrofit2.http.Query

interface ChatApi {

    @GET("/api/chats")
    suspend fun getChatsForUser(): List<ChatDto>

    @GET("/api/chat/messages")
    suspend fun getMessagesForChat(
        @Query("chatId") chatId: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): List<MessageDto>

    companion object {
        //        const val BASE_URL = "http://192.168.48.34:8001/"
        const val BASE_URL = Constants.DEBUG_BASE_URL
    }

}