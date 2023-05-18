package com.fifty.socialnetwork.core.domain.repository

import android.net.Uri
import androidx.paging.PagingData
import com.fifty.socialnetwork.core.domain.models.Post
import com.fifty.socialnetwork.core.domain.models.UserItem
import com.fifty.socialnetwork.core.util.Constants
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.core.util.SimpleResource
import com.fifty.socialnetwork.featureprofile.domain.model.Profile
import com.fifty.socialnetwork.featureprofile.domain.model.Skill
import com.fifty.socialnetwork.featureprofile.domain.model.UpdateProfileData
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    suspend fun getPostsPaged(
        page: Int = 0,
        pageSize: Int = Constants.DEFAULT_PAGE_SIZE,
        userId: String
    ): Resource<List<Post>>

    suspend fun getProfile(userId: String): Resource<Profile>

    suspend fun updateProfile(
        updateProfileData: UpdateProfileData,
        bannerImageUri: Uri?,
        profilePictureUri: Uri?
    ): SimpleResource

    suspend fun getSkills(): Resource<List<Skill>>

    suspend fun searchUser(query: String): Resource<List<UserItem>>

    suspend fun followUser(userId: String): SimpleResource

    suspend fun unfollowUser(userId: String): SimpleResource
}