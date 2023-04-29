package com.fifty.socialnetwork.domain.model

import com.fifty.socialnetwork.domain.util.ActivityAction

data class Activity(
    val username: String,
    val actionType: ActivityAction,
    val formattedTime: String,
)
