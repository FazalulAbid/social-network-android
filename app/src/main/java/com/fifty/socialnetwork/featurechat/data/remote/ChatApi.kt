package com.fifty.socialnetwork.featurechat.data.remote

import com.fifty.socialnetwork.core.util.Constants
import com.fifty.socialnetwork.featurechat.data.remote.data.ChatDto
import retrofit2.http.GET

interface ChatApi {

    @GET("/api/chats")
    suspend fun getChatsForUser(): List<ChatDto>

    companion object {
        //        const val BASE_URL = "http://192.168.48.34:8001/"
        const val BASE_URL = Constants.DEBUG_BASE_URL
    }

}