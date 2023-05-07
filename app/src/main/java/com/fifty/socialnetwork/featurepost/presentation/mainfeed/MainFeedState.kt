package com.fifty.socialnetwork.featurepost.presentation.mainfeed

import com.fifty.socialnetwork.core.domain.models.Post

data class MainFeedState(
    val isLoadingFirstTime: Boolean = true,
    val isLoadingNewPost: Boolean = false,
)
