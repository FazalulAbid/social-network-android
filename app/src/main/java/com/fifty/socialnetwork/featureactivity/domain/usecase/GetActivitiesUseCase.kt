package com.fifty.socialnetwork.featureactivity.domain.usecase

import androidx.paging.PagingData
import com.fifty.socialnetwork.core.domain.models.Activity
import com.fifty.socialnetwork.featureactivity.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow

class GetActivitiesUseCase(
    private val repository: ActivityRepository
) {

    operator fun invoke(): Flow<PagingData<Activity>> {
        return repository.activities
    }
}