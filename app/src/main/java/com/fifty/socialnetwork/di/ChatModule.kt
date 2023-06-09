package com.fifty.socialnetwork.di

import com.fifty.socialnetwork.core.util.Constants
import com.fifty.socialnetwork.featurechat.data.remote.ChatApi
import com.fifty.socialnetwork.featurechat.data.remote.ChatService
import com.fifty.socialnetwork.featurechat.data.remote.util.CustomGsonMessageAdapter
import com.fifty.socialnetwork.featurechat.data.repository.ChatRepositoryImpl
import com.fifty.socialnetwork.featurechat.domain.repository.ChatRepository
import com.fifty.socialnetwork.featurechat.domain.usecase.ChatUseCases
import com.fifty.socialnetwork.featurechat.domain.usecase.GetChatsForUser
import com.fifty.socialnetwork.featurechat.domain.usecase.GetMessagesForChat
import com.fifty.socialnetwork.featurechat.domain.usecase.InitializeRepository
import com.fifty.socialnetwork.featurechat.domain.usecase.ObserveChatEvents
import com.fifty.socialnetwork.featurechat.domain.usecase.ObserveMessages
import com.fifty.socialnetwork.featurechat.domain.usecase.SendMessage
import com.google.gson.Gson
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.retry.LinearBackoffStrategy
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import com.tinder.streamadapter.coroutines.CoroutinesStreamAdapterFactory
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

@Module
@InstallIn(SingletonComponent::class)
object ChatModule {

    @Provides
    fun provideChatRepository(client: OkHttpClient, chatApi: ChatApi): ChatRepository {
        return ChatRepositoryImpl(chatApi, client)
    }

    @Provides
    fun provideChatUseCases(repository: ChatRepository): ChatUseCases {
        return ChatUseCases(
            sendMessage = SendMessage(repository),
            observeChatEvents = ObserveChatEvents(repository),
            observeMessages = ObserveMessages(repository),
            getChatsForUser = GetChatsForUser(repository),
            getMessagesForChat = GetMessagesForChat(repository),
            initializeRepository = InitializeRepository(repository)
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