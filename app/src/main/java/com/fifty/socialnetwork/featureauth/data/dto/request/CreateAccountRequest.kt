package com.fifty.socialnetwork.featureauth.data.dto.request

data class CreateAccountRequest(
    val email: String,
    val username: String,
    val password: String
)
