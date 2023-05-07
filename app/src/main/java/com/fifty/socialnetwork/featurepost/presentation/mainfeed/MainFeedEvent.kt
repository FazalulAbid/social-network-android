package com.fifty.socialnetwork.featurepost.presentation.mainfeed

sealed class MainFeedEvent {

    object LoadMorePosts: MainFeedEvent()
    object LoadedPage: MainFeedEvent()
}
