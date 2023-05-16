package com.fifty.socialnetwork.featureactivity.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fifty.socialnetwork.core.domain.models.Activity
import com.fifty.socialnetwork.core.domain.models.Post
import com.fifty.socialnetwork.core.util.Constants
import com.fifty.socialnetwork.featureactivity.data.paging.ActivitySource
import com.fifty.socialnetwork.featureactivity.data.remote.ActivityApi
import com.fifty.socialnetwork.featureactivity.domain.repository.ActivityRepository
import com.fifty.socialnetwork.featurepost.data.paging.PostSource
import kotlinx.coroutines.flow.Flow

class ActivityRepositoryImpl(
    private val api: ActivityApi
) : ActivityRepository {

    override val activities: Flow<PagingData<Activity>>
        get() = Pager(PagingConfig(pageSize = Constants.DEFAULT_PAGE_SIZE)) {
            ActivitySource(api)
        }.flow
}