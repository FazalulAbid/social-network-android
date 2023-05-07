package com.fifty.socialnetwork.featureauth.domain.usecase

import com.fifty.socialnetwork.core.domain.util.ValidationUtil
import com.fifty.socialnetwork.featureauth.domain.models.LoginResult
import com.fifty.socialnetwork.featureauth.domain.repository.AuthRepository
import com.fifty.socialnetwork.featureauth.util.AuthError

class LoginUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(email: String, password: String): LoginResult {
        val emailError = if (email.isBlank()) AuthError.FieldEmpty else null
        val passwordError = if (password.isBlank()) AuthError.FieldEmpty else null

        if (emailError != null || passwordError != null) {
            return LoginResult(emailError, passwordError)
        }

        return LoginResult(
            result = repository.login(email, password)
        )
    }
}