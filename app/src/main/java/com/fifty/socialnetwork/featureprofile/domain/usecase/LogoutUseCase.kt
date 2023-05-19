package com.fifty.socialnetwork.featureprofile.domain.usecase

import com.fifty.socialnetwork.core.domain.repository.ProfileRepository

class LogoutUseCase(
    private val repository: ProfileRepository
) {

    operator fun invoke() {
        repository.logout()
    }
}