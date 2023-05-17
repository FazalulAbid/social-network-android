package com.fifty.socialnetwork.featurepost.domain.usecase

import com.fifty.socialnetwork.core.util.SimpleResource
import com.fifty.socialnetwork.featurepost.domain.repository.PostRepository

class ToggleLikeForParentUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke(
        parentId: String,
        parentType: Int,
        isLiked: Boolean
    ): SimpleResource {
        return if (isLiked) {
            repository.unlikeParent(parentId, parentType)
        } else {
            repository.likeParent(parentId, parentType)
        }
    }
}