package com.fifty.socialnetwork.di

import android.app.Application
import com.fifty.socialnetwork.core.util.Constants
import com.fifty.socialnetwork.featurechat.data.remote.ws.util.CustomGsonMessageAdapter
import com.fifty.socialnetwork.featurechat.data.remote.ws.util.FlowStreamAdapter
import com.google.gson.Gson
import com.tinder.scarlet.Scarlet
import com.tinder.scarlet.lifecycle.android.AndroidLifecycle
import com.tinder.scarlet.websocket.okhttp.newWebSocketFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
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
}