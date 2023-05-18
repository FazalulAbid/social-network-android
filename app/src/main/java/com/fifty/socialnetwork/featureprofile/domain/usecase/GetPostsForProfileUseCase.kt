package com.fifty.socialnetwork.featureprofile.domain.usecase

import androidx.paging.PagingData
import com.fifty.socialnetwork.core.domain.models.Post
import com.fifty.socialnetwork.core.domain.repository.ProfileRepository
import com.fifty.socialnetwork.core.util.Resource
import kotlinx.coroutines.flow.Flow

class GetPostsForProfileUseCase(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(userId: String, page: Int): Resource<List<Post>> {
        return repository.getPostsPaged(
            userId = userId,
            page = page
        )
    }
}