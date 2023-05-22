package com.fifty.socialnetwork.featurepost.presentation.mainfeed

import com.fifty.socialnetwork.core.domain.models.Post

sealed class MainFeedEvent {
    data class LikedPost(val postId:String): MainFeedEvent()
    data class DeletePost(val post: Post): MainFeedEvent()
}
