package com.fifty.socialnetwork.featureauth.presentation.register

import com.fifty.socialnetwork.core.util.UiText

data class RegisterState(
    val successful: Boolean? = null,
    val message: UiText? = null,
    val isLoading: Boolean = false
)
