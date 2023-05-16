package com.fifty.socialnetwork.core.domain.states
import com.fifty.socialnetwork.core.util.Error

data class StandardTextFieldState(
    val text: String = "",
    val error: Error? = null
)