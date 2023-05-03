package com.fifty.socialnetwork.core.domain.models

import com.fifty.socialnetwork.domain.util.ActivityAction

data class Activity(
    val username: String,
    val actionType: ActivityAction,
    val formattedTime: String,
)
