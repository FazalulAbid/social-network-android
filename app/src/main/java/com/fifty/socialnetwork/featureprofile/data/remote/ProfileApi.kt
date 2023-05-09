package com.fifty.socialnetwork.featureprofile.data.remote

import com.fifty.socialnetwork.core.data.dto.response.BasicApiResponse
import com.fifty.socialnetwork.core.util.Constants
import com.fifty.socialnetwork.featureprofile.data.remote.response.ProfileResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfileApi {

    @GET("/api/user/profile")
    suspend fun getProfile(
        @Query("userId") userId: String
    ): BasicApiResponse<ProfileResponse>

    companion object {
        //        const val BASE_URL = "http://192.168.48.34:8001/"
        const val BASE_URL = Constants.DEBUG_BASE_URL
    }
}