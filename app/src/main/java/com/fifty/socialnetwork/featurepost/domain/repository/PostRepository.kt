package com.fifty.socialnetwork.featurepost.domain.repository

import android.net.Uri
import androidx.paging.PagingData
import com.fifty.socialnetwork.core.domain.models.Comment
import com.fifty.socialnetwork.core.domain.models.Post
import com.fifty.socialnetwork.core.domain.models.UserItem
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.core.util.SimpleResource
import com.fifty.socialnetwork.featurepost.data.remote.dto.UserItemDto
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PostRepository {

    suspend fun getPostsForFollows(page: Int, pageSize: Int): Resource<List<Post>>

    suspend fun createPost(description: String, imageUri: Uri): SimpleResource

    suspend fun getPostDetails(postId: String): Resource<Post>

    suspend fun getCommentsForPost(postId: String): Resource<List<Comment>>

    suspend fun createComment(postId: String, comment: String): SimpleResource

    suspend fun likeParent(parentId: String, parentType: Int): SimpleResource

    suspend fun unlikeParent(parentId: String, parentType: Int): SimpleResource

    suspend fun getLikesForParent(parentId: String): Resource<List<UserItem>>

    suspend fun deletePost(postId: String): SimpleResource
}