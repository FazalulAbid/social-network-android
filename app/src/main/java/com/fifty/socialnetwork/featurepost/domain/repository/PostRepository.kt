package com.fifty.socialnetwork.featurepost.domain.repository

import androidx.paging.PagingData
import com.fifty.socialnetwork.core.domain.models.Post
import com.fifty.socialnetwork.core.util.Constants
import com.fifty.socialnetwork.core.util.Resource
import kotlinx.coroutines.flow.Flow

interface PostRepository {

    val posts: Flow<PagingData<Post>>
}