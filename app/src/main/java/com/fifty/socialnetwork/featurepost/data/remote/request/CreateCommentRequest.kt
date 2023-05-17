package com.fifty.socialnetwork.featurepost.data.remote.request

data class CreateCommentRequest(
    val comment: String,
    val postId: String,
)
