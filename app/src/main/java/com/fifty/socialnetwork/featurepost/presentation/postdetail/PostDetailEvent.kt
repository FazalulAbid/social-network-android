package com.fifty.socialnetwork.featurepost.presentation.postdetail

sealed class PostDetailEvent {
    object LikePost : PostDetailEvent()
    object Comment : PostDetailEvent()
    data class LikeComment(val commentId: String) : PostDetailEvent()
    object SharePost : PostDetailEvent()
    data class EnteredComment(val comment:String): PostDetailEvent()
}
