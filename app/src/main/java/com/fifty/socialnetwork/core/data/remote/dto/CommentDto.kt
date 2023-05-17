package com.fifty.socialnetwork.core.data.remote.dto

import com.fifty.socialnetwork.core.domain.models.Comment
import java.text.SimpleDateFormat
import java.util.Locale

data class CommentDto(
    val id: String,
    val username: String,
    val profileImageUrl: String,
    val timestamp: Long,
    val comment: String,
    val isLiked: Boolean,
    val likeCount: Int
) {
    fun toComment(): Comment {
        return Comment(
            id = id,
            username = username,
            profileImageUrl = profileImageUrl,
            formattedTime = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault()).run {
                format(timestamp)
            },
            comment = comment,
            isLiked = isLiked,
            likeCount = likeCount
        )
    }
}