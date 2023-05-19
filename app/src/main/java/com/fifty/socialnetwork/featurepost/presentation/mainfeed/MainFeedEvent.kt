package com.fifty.socialnetwork.featurepost.presentation.mainfeed

sealed class MainFeedEvent {
    data class LikedPost(val postId:String): MainFeedEvent()
}
