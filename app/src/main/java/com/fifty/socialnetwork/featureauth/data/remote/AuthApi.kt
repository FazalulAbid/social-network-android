package com.fifty.socialnetwork.featureauth.data.remote

import com.fifty.socialnetwork.core.data.dto.response.BasicApiResponse
import com.fifty.socialnetwork.core.util.Constants
import com.fifty.socialnetwork.featureauth.data.remote.request.CreateAccountRequest
import com.fifty.socialnetwork.featureauth.data.remote.request.LoginRequest
import com.fifty.socialnetwork.featureauth.data.remote.response.AuthResponse
import retrofit2.http.Body
import retrofit2.http.GET
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

    @GET("/api/user/authenticate")
    suspend fun authenticate()

    companion object {
        //        const val BASE_URL = "http://192.168.48.34:8001/"
        const val BASE_URL = Constants.DEBUG_BASE_URL
    }
}