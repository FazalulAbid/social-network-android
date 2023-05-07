package com.fifty.socialnetwork.featurepost.data.repository

import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.core.domain.models.Post
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.core.util.UiText
import com.fifty.socialnetwork.featurepost.data.datasource.remote.PostApi
import com.fifty.socialnetwork.featurepost.domain.repository.PostRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PostRepositoryImpl(
    private val api: PostApi
) : PostRepository {

    override suspend fun getPostForFollows(page: Int, pageSize: Int): Resource<List<Post>> {
        return try {
            val posts = api.getPostsForFollows(page = page, pageSize = pageSize)
            Resource.Success(posts)
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_could_not_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }
}