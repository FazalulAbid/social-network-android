package com.fifty.socialnetwork.featureauth.domain.usecase

import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.core.util.SimpleResource
import com.fifty.socialnetwork.featureauth.domain.repository.AuthRepository

class AuthenticateUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(): SimpleResource {
        return repository.authenticate()
    }
}