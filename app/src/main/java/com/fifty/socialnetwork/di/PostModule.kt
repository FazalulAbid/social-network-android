package com.fifty.socialnetwork.di

import com.fifty.socialnetwork.featurepost.data.datasource.remote.PostApi
import com.fifty.socialnetwork.featurepost.data.repository.PostRepositoryImpl
import com.fifty.socialnetwork.featurepost.domain.repository.PostRepository
import com.fifty.socialnetwork.featurepost.domain.usecase.GetPostsForFollowsUseCase
import com.fifty.socialnetwork.featurepost.domain.usecase.PostUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun providePostRepository(api: PostApi): PostRepository {
        return PostRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providePostUseCases(repository: PostRepository): PostUseCases {
        return PostUseCases(
            getPostsForFollowsUseCase = GetPostsForFollowsUseCase(repository)
        )
    }
}