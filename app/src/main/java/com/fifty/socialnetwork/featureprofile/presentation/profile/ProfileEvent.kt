package com.fifty.socialnetwork.featureprofile.presentation.profile

sealed class ProfileEvent {
    data class GetProfile(val userId: String) : ProfileEvent()
    data class LikePost(val postId: String) : ProfileEvent()
}
