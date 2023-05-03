package com.fifty.socialnetwork.core.domain.models

import android.provider.ContactsContract.CommonDataKinds.StructuredName

data class User(
    val profilePictureUrl: String,
    val username: String,
    val description: String,
    val followerCount: Int,
    val followingCount: Int,
    val postCount: Int
)
