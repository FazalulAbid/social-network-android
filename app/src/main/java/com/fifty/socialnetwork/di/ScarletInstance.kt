package com.fifty.socialnetwork.di

import com.fifty.socialnetwork.core.util.Constants
import com.fifty.socialnetwork.featurechat.data.remote.ChatService
import com.fifty.socialnetwork.featurechat.data.remote.util.CustomGsonMessageAdapter
import com.google.gson.Gson
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.retry.LinearBackoffStrategy
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import com.tinder.streamadapter.coroutines.CoroutinesStreamAdapterFactory
import okhttp3.OkHttpClient

object ScarletInstance {

    var current: ChatService? = null

    fun getNewInstance(client: OkHttpClient): ChatService {
        return Scarlet.Builder()
            .addMessageAdapterFactory(CustomGsonMessageAdapter.Factory(Gson()))
            .addStreamAdapterFactory(CoroutinesStreamAdapterFactory())
            .webSocketFactory(client.newWebSocketFactory(Constants.DEBUG_WS_BASE_URL))
            .backoffStrategy(LinearBackoffStrategy(Constants.RECONNECT_INTERVAL))
            .build()
            .create(ChatService::class.java)
            .also {
                current = it
            }
    }
}