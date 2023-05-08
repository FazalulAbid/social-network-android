package com.fifty.socialnetwork.featurepost.presentation.mainfeed

data class MainFeedState(
    val isLoadingFirstTime: Boolean = true,
    val isLoadingNewPost: Boolean = false,
)
