package com.fifty.socialnetwork.featurepost.domain.repository

import android.net.Uri
import androidx.paging.PagingData
import com.fifty.socialnetwork.core.domain.models.Comment
import com.fifty.socialnetwork.core.domain.models.Post
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.core.util.SimpleResource
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PostRepository {

    val posts: Flow<PagingData<Post>>

    suspend fun createPost(description: String, imageUri: Uri): SimpleResource

    suspend fun getPostDetails(postId: String): Resource<Post>

    suspend fun getCommentsForPost(postId: String): Resource<List<Comment>>
}