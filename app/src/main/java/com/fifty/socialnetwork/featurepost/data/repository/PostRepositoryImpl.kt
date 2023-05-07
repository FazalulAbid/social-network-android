package com.fifty.socialnetwork.featurepost.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.core.domain.models.Post
import com.fifty.socialnetwork.core.util.Constants
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.core.util.UiText
import com.fifty.socialnetwork.featurepost.data.datasource.remote.PostApi
import com.fifty.socialnetwork.featurepost.data.paging.PostSource
import com.fifty.socialnetwork.featurepost.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostRepositoryImpl(
    private val api: PostApi
) : PostRepository {

    override val posts: Flow<PagingData<Post>>
        get() = Pager(PagingConfig(pageSize = Constants.PAGE_SIZE_POSTS)) {
            PostSource(api)
        }.flow
}