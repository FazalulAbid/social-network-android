package com.fifty.socialnetwork.core.data.remote

import com.fifty.socialnetwork.core.data.dto.response.BasicApiResponse
import com.fifty.socialnetwork.core.data.remote.dto.CommentDto
import com.fifty.socialnetwork.core.domain.models.Comment
import com.fifty.socialnetwork.core.domain.models.Post
import com.fifty.socialnetwork.core.util.Constants
import com.fifty.socialnetwork.featurepost.data.remote.request.CreatePostRequest
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface PostApi {

    @GET("/api/post/get")
    suspend fun getPostsForFollows(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): List<Post>

    @GET("/api/user/posts")
    suspend fun getPostForProfile(
        @Query("userId") userId: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): List<Post>

    @Multipart
    @POST("/api/post/create")
    suspend fun createPost(
        @Part postData: MultipartBody.Part,
        @Part postImage: MultipartBody.Part
    ): BasicApiResponse<Unit>

    @GET("/api/post/details")
    suspend fun getPostDetails(
        @Query("postId") postId: String
    ): BasicApiResponse<Post>

    @GET("/api/comment/get")
    suspend fun getCommentsForPost(
        @Query("postId") postId: String
    ): List<CommentDto>

    companion object {
        //        const val BASE_URL = "http://192.168.48.34:8001/"
        const val BASE_URL = Constants.DEBUG_BASE_URL
    }
}