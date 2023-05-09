package com.fifty.socialnetwork.featurepost.domain.usecase

import android.net.Uri
import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.core.util.SimpleResource
import com.fifty.socialnetwork.core.util.UiText
import com.fifty.socialnetwork.featurepost.domain.repository.PostRepository

class CreatePostUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke(
        description: String,
        imageUri: Uri?
    ): SimpleResource {
        if (imageUri == null) {
            return Resource.Error(
                uiText = UiText.StringResource(
                    R.string.error_no_image_picked
                )
            )
        }
        if (description.isBlank()) {
            return Resource.Error(
                uiText = UiText.StringResource(
                    R.string.error_description_blank
                )
            )
        }
        return repository.createPost(description, imageUri)
    }
}