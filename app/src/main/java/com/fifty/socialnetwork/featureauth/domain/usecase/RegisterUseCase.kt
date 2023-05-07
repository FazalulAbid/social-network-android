package com.fifty.socialnetwork.featureauth.domain.usecase

import com.fifty.socialnetwork.core.util.SimpleResource
import com.fifty.socialnetwork.featureauth.domain.repository.AuthRepository

class RegisterUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(
        email: String,
        username: String,
        password: String
    ): SimpleResource {
        return repository.register(email.trim(), username.trim(), password.trim())
    }
}