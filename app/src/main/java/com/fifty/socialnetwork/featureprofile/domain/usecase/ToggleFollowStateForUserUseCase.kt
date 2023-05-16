package com.fifty.socialnetwork.featureprofile.domain.usecase

import com.fifty.socialnetwork.core.util.SimpleResource
import com.fifty.socialnetwork.featureprofile.domain.repository.ProfileRepository

class ToggleFollowStateForUserUseCase(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(userId: String, isFollowing: Boolean): SimpleResource {
        return if (isFollowing) {
            repository.unfollowUser(userId)
        } else {
            repository.followUser(userId)
        }
    }
}