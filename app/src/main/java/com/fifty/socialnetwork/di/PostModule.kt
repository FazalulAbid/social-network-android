package com.fifty.socialnetwork.di

import com.fifty.socialnetwork.core.domain.usecase.DeletePost
import com.fifty.socialnetwork.featurepost.data.remote.PostApi
import com.fifty.socialnetwork.featurepost.data.repository.PostRepositoryImpl
import com.fifty.socialnetwork.featurepost.domain.repository.PostRepository
import com.fifty.socialnetwork.featurepost.domain.usecase.CreateCommentUseCase
import com.fifty.socialnetwork.featurepost.domain.usecase.CreatePostUseCase
import com.fifty.socialnetwork.featurepost.domain.usecase.GetCommentsForPostUseCase
import com.fifty.socialnetwork.featurepost.domain.usecase.GetLikesForParentUseCase
import com.fifty.socialnetwork.featurepost.domain.usecase.GetPostDetailsUseCase
import com.fifty.socialnetwork.featurepost.domain.usecase.GetPostsForFollowsUseCase
import com.fifty.socialnetwork.featurepost.domain.usecase.PostUseCases
import com.fifty.socialnetwork.featurepost.domain.usecase.ToggleLikeForParentUseCase
import com.google.gson.Gson
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
            getPostsForFollows = GetPostsForFollowsUseCase(repository),
            createPostUseCase = CreatePostUseCase(repository),
            getPostDetails = GetPostDetailsUseCase(repository),
            getCommentsForPost = GetCommentsForPostUseCase(repository),
            createComment = CreateCommentUseCase(repository),
            toggleLikeForParent = ToggleLikeForParentUseCase(repository),
            getLikesForParent = GetLikesForParentUseCase(repository),
            deletePost = DeletePost(repository)
        )
    }
}