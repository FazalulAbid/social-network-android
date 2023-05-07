package com.fifty.socialnetwork.featurepost.data.datasource.remote

import com.fifty.socialnetwork.core.domain.models.Post
import retrofit2.http.GET
import retrofit2.http.Query

interface PostApi {

    @GET("/api/post/get")
    suspend fun getPostsForFollows(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): List<Post>

    companion object {
        const val BASE_URL = "http://192.168.48.34:8001/"
    }
}