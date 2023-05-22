package com.fifty.socialnetwork.featureprofile.presentation.profile

import com.fifty.socialnetwork.core.domain.models.Post

sealed class ProfileEvent {
    data class LikePost(val postId: String) : ProfileEvent()
    data class DeletePost(val post:Post) : ProfileEvent()
    object DismissLogoutDialog : ProfileEvent()
    object ShowLogoutDialog : ProfileEvent()
    object Logout: ProfileEvent()
}
