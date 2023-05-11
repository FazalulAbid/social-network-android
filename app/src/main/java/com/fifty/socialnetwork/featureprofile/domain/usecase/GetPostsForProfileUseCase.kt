package com.fifty.socialnetwork.featureprofile.domain.usecase

import androidx.paging.PagingData
import com.fifty.socialnetwork.core.domain.models.Post
import com.fifty.socialnetwork.featureprofile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow

class GetPostsForProfileUseCase(
    private val repository: ProfileRepository
) {

    operator fun invoke(userId: String): Flow<PagingData<Post>> {
        return repository.getPostsPaged(userId)
    }
}