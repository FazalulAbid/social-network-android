package com.fifty.socialnetwork.di

import android.content.Context
import com.fifty.socialnetwork.featurepost.data.remote.PostApi
import com.fifty.socialnetwork.featurepost.data.repository.PostRepositoryImpl
import com.fifty.socialnetwork.featurepost.domain.repository.PostRepository
import com.fifty.socialnetwork.featurepost.domain.usecase.CreatePostUseCase
import com.fifty.socialnetwork.featurepost.domain.usecase.GetPostsForFollowsUseCase
import com.fifty.socialnetwork.featurepost.domain.usecase.PostUseCases
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PostModule {

    @Provides
    @Singleton
    fun providePostApi(client: OkHttpClient): PostApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(PostApi.BASE_URL)
            .client(client)
            .build()
            .create(PostApi::class.java)
    }

    @Provides
    @Singleton
    fun providePostRepository(
        api: PostApi,
        gson: Gson
    ): PostRepository {
        return PostRepositoryImpl(api, gson)
    }

    @Provides
    @Singleton
    fun providePostUseCases(repository: PostRepository): PostUseCases {
        return PostUseCases(
            getPostsForFollowsUseCase = GetPostsForFollowsUseCase(repository),
            createPostUseCase = CreatePostUseCase(repository)
        )
    }
}