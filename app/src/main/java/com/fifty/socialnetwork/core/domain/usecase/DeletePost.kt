package com.fifty.socialnetwork.core.domain.usecase

import com.fifty.socialnetwork.core.domain.models.Post
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.core.util.SimpleResource
import com.fifty.socialnetwork.featurepost.domain.repository.PostRepository

class DeletePost(
    private val repository: PostRepository
) {

    suspend operator fun invoke(postId:String): SimpleResource {
        return repository.deletePost(postId)
    }
}