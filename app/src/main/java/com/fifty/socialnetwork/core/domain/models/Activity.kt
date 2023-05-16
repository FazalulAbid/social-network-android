package com.fifty.socialnetwork.core.domain.models

import com.fifty.socialnetwork.domain.util.ActivityType

data class Activity(
    val userId: String,
    val parentId: String,
    val username: String,
    val activityType: ActivityType,
    val formattedTime: String,
)
