package com.fifty.socialnetwork.featurepost.domain.usecase

import com.fifty.socialnetwork.core.domain.models.UserItem
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.featurepost.domain.repository.PostRepository

class GetLikesForParentUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke(parentId: String): Resource<List<UserItem>> {
        return repository.getLikesForParent(parentId)
    }
}