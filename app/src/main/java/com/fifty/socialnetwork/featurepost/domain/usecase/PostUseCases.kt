package com.fifty.socialnetwork.featurepost.domain.usecase

data class PostUseCases(
    val getPostsForFollowsUseCase: GetPostsForFollowsUseCase,
    val createPostUseCase: CreatePostUseCase
)
