package com.fifty.socialnetwork.featurepost.domain.usecase

import com.fifty.socialnetwork.core.domain.models.Post
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.featurepost.domain.repository.PostRepository

class GetPostDetailsUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke(postId: String): Resource<Post> {
        return repository.getPostDetails(postId)
    }
}