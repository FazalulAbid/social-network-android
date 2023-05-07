package com.fifty.socialnetwork.featurepost.domain.usecase

import com.fifty.socialnetwork.core.domain.models.Post
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.featurepost.domain.repository.PostRepository

class GetPostsForFollowsUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke(
        page: Int,
        pageSize: Int
    ): Resource<List<Post>> {
        return repository.getPostForFollows(
            page = page,
            pageSize = pageSize
        )
    }
}