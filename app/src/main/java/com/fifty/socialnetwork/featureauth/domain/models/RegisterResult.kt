package com.fifty.socialnetwork.featureauth.domain.models

import com.fifty.socialnetwork.core.util.SimpleResource
import com.fifty.socialnetwork.featureauth.util.AuthError

data class RegisterResult(
    val emailError: AuthError? = null,
    val usernameError: AuthError? = null,
    val passwordError: AuthError? = null,
    val result: SimpleResource? = null
)