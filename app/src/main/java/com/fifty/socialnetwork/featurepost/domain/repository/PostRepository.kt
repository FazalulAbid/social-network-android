package com.fifty.socialnetwork.featurepost.domain.repository

import com.fifty.socialnetwork.core.domain.models.Post
import com.fifty.socialnetwork.core.util.Constants
import com.fifty.socialnetwork.core.util.Resource

interface PostRepository {

    suspend fun getPostForFollows(
        page: Int = 0,
        pageSize: Int = Constants.PAGE_SIZE_POSTS
    ): Resource<List<Post>>
}