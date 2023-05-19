package com.fifty.socialnetwork.core.util

import com.fifty.socialnetwork.core.domain.models.Post

interface PostLiker {

    suspend fun toggleLike(
        parentId: String,
        posts: List<Post>,
        onRequest: suspend (isLiked: Boolean) -> SimpleResource,
        onStateUpdated: (List<Post>) -> Unit
    )
}