package com.fifty.socialnetwork.featureprofile.domain.usecase

import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.featureprofile.domain.model.Profile
import com.fifty.socialnetwork.core.domain.repository.ProfileRepository

class GetProfileUseCase(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(userId: String): Resource<Profile> {
        return repository.getProfile(userId)
    }
}