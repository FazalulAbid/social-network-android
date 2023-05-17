package com.fifty.socialnetwork.featurepost.domain.usecase

import com.fifty.socialnetwork.core.domain.models.Comment
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.featurepost.domain.repository.PostRepository

class GetCommentsForPostUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke(postId: String): Resource<List<Comment>> {
        return repository.getCommentsForPost(postId)
    }
}