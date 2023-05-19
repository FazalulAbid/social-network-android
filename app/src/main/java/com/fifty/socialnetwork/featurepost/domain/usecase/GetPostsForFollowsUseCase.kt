package com.fifty.socialnetwork.featurepost.domain.usecase

import androidx.paging.PagingData
import com.fifty.socialnetwork.core.domain.models.Post
import com.fifty.socialnetwork.core.util.Constants
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.featurepost.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class GetPostsForFollowsUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke(
        page: Int,
        pageSize: Int = Constants.DEFAULT_PAGE_SIZE
    ): Resource<List<Post>> {
        return repository.getPostsForFollows(page, pageSize)
    }
}