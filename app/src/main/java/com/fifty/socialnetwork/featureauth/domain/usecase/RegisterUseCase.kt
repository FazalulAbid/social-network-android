package com.fifty.socialnetwork.featureauth.domain.usecase

import android.util.Patterns
import com.fifty.socialnetwork.core.domain.util.ValidationUtil
import com.fifty.socialnetwork.core.util.Constants
import com.fifty.socialnetwork.core.util.SimpleResource
import com.fifty.socialnetwork.featureauth.domain.models.RegisterResult
import com.fifty.socialnetwork.featureauth.domain.repository.AuthRepository
import com.fifty.socialnetwork.featureauth.util.AuthError

class RegisterUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(
        email: String,
        username: String,
        password: String
    ): RegisterResult {
        val emailError = ValidationUtil.validateEmail(email)
        val usernameError = ValidationUtil.validateUsername(username)
        val passwordError = ValidationUtil.validatePassword(password)

        if (emailError != null || usernameError != null || passwordError != null) {
            return RegisterResult(
                emailError = emailError,
                usernameError = usernameError,
                passwordError = passwordError
            )
        }

        val result = repository.register(email.trim(), username.trim(), password.trim())

        return RegisterResult(
            result = result
        )
    }
}