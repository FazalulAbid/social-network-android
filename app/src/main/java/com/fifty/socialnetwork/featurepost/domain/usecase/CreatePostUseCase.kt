package com.fifty.socialnetwork.featurepost.domain.usecase

import android.net.Uri
import com.fifty.socialnetwork.core.util.SimpleResource
import com.fifty.socialnetwork.featurepost.domain.repository.PostRepository

class CreatePostUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke(
        description: String,
        imageUri: Uri
    ): SimpleResource {
        return repository.createPost(
            description,
            imageUri
        )
    }
}