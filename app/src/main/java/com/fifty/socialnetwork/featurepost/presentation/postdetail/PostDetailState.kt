package com.fifty.socialnetwork.featurepost.presentation.postdetail

import com.fifty.socialnetwork.core.domain.models.Comment
import com.fifty.socialnetwork.core.domain.models.Post

data class PostDetailState(
    val post: Post? = null,
    val comments: List<Comment> = emptyList(),
    val isLoadingPost: Boolean = false,
    val isLoadingComments: Boolean = false
)
