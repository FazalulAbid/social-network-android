package com.fifty.socialnetwork.featureauth.data.remote

import com.fifty.socialnetwork.core.data.dto.response.BasicApiResponse
import com.fifty.socialnetwork.featureauth.data.dto.request.CreateAccountRequest
import com.fifty.socialnetwork.featureauth.data.dto.request.LoginRequest
import com.fifty.socialnetwork.featureauth.data.dto.response.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/user/create")
    suspend fun register(
        @Body request: CreateAccountRequest
    ): BasicApiResponse<Unit>

    @POST("/api/user/login")
    suspend fun login(
        @Body request: LoginRequest
    ): BasicApiResponse<AuthResponse>

    companion object {
        const val BASE_URL = "http://192.168.48.34:8001/"
    }
}