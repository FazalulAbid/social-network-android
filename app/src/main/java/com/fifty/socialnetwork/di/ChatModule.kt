package com.fifty.socialnetwork.di

import android.app.Application
import com.fifty.socialnetwork.core.util.Constants
import com.fifty.socialnetwork.featurechat.data.remote.ChatApi
import com.fifty.socialnetwork.featurechat.data.remote.ChatService
import com.fifty.socialnetwork.featurechat.data.remote.data.repository.ChatRepositoryImpl
import com.fifty.socialnetwork.featurechat.data.remote.util.CustomGsonMessageAdapter
import com.fifty.socialnetwork.featurechat.data.remote.util.FlowStreamAdapter
import com.fifty.socialnetwork.featurechat.domain.repository.ChatRepository
import com.fifty.socialnetwork.featurechat.domain.usecase.ChatUseCases
import com.fifty.socialnetwork.featurechat.domain.usecase.GetChatsForUser
import com.fifty.socialnetwork.featurechat.domain.usecase.ObserveChatEvents
import com.fifty.socialnetwork.featurechat.domain.usecase.ObserveMessages
import com.fifty.socialnetwork.featurechat.domain.usecase.SendMessage
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.lifecycle.android.AndroidLifecycle
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@OptIn(ExperimentalCoroutinesApi::class)
@Module
@InstallIn(SingletonComponent::class)
object ChatModule {

    @Provides
    @Singleton
    fun provideScarlet(app: Application, gson: Gson, client: OkHttpClient): Scarlet {
        return Scarlet.Builder()
            .addMessageAdapterFactory(CustomGsonMessageAdapter.Factory(gson))
            .addStreamAdapterFactory(FlowStreamAdapter.Factory)
            .webSocketFactory(client.newWebSocketFactory(Constants.DEBUG_WS_BASE_URL))
            .lifecycle(AndroidLifecycle.ofApplicationForeground(app))
            .build()
    }

    @Provides
    @Singleton
    fun provideChatService(scarlet: Scarlet): ChatService {
        return scarlet.create()
    }

    @Provides
    @Singleton
    fun provideChatRepository(chatService: ChatService, chatApi: ChatApi): ChatRepository {
        return ChatRepositoryImpl(chatService, chatApi)
    }

    @Provides
    @Singleton
    fun provideChatUseCases(repository: ChatRepository): ChatUseCases {
        return ChatUseCases(
            sendMessage = SendMessage(repository),
            observeChatEvents = ObserveChatEvents(repository),
            observeMessages = ObserveMessages(repository),
            getChatsForUser = GetChatsForUser(repository)
        )
    }

    @Provides
    @Singleton
    fun provideChatApi(client: OkHttpClient): ChatApi {
        return Retrofit.Builder()
            .baseUrl(ChatApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create()
    }
}