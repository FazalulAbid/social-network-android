package com.fifty.socialnetwork.featureactivity.data.remote

import com.fifty.data.responses.ActivityDto
import com.fifty.socialnetwork.core.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ActivityApi {

    @GET("/api/activity/get")
    suspend fun getActivities(
        @Query("page") page: Int = 0,
        @Query("pageSize") pageSize: Int = Constants.DEFAULT_PAGE_SIZE
    ): List<ActivityDto>

    companion object {
        const val BASE_URL = Constants.DEBUG_BASE_URL
    }
}