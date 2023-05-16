package com.fifty.socialnetwork.featureactivity.domain.repository

import androidx.paging.PagingData
import com.fifty.socialnetwork.core.domain.models.Activity
import com.fifty.socialnetwork.core.domain.models.Post
import kotlinx.coroutines.flow.Flow

interface ActivityRepository {

    val activities: Flow<PagingData<Activity>>
}