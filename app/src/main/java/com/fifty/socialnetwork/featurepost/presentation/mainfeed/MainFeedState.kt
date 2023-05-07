package com.fifty.socialnetwork.featurepost.presentation.mainfeed

import com.fifty.socialnetwork.core.domain.models.Post

data class MainFeedState(
    val posts: List<Post> = emptyList(),
    val isLoading: Boolean = false,
    val page: Int = 0
)
