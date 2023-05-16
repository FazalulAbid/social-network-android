package com.fifty.socialnetwork.featureactivity.presentation

import com.fifty.socialnetwork.core.domain.models.Activity

data class ActivityState(
    val activities: List<Activity> = emptyList(),
    val isLoading: Boolean = false
)
