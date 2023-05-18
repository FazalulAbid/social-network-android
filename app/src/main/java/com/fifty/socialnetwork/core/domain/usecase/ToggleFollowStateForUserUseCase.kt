package com.fifty.socialnetwork.core.domain.usecase

import com.fifty.socialnetwork.core.util.SimpleResource
import com.fifty.socialnetwork.core.domain.repository.ProfileRepository

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