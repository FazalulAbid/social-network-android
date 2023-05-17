package com.fifty.socialnetwork.featurepost.domain.usecase

import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.core.util.SimpleResource
import com.fifty.socialnetwork.core.util.UiText
import com.fifty.socialnetwork.featurepost.domain.repository.PostRepository

class CreateCommentUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke(postId: String, comment: String): SimpleResource {
        if (comment.isBlank()) {
            return Resource.Error(UiText.StringResource(R.string.this_field_cant_be_empty))
        }
        if (postId.isBlank()) {
            return Resource.Error(UiText.unknownError())
        }
        return repository.createComment(postId, comment)
    }
}