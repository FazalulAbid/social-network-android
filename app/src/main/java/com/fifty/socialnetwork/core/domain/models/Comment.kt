package com.fifty.socialnetwork.core.domain.models

data class Comment(
    val id: String,
    val username: String,
    val profileImageUrl: String,
    val formattedTime: String,
    val comment: String,
    val isLiked: Boolean,
    val likeCount: Int
)
