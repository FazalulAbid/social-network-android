package com.fifty.socialnetwork.featurepost.data.remote.request


data class LikeUpdateRequest(
    val parentId: String,
    val parentType: Int
)
