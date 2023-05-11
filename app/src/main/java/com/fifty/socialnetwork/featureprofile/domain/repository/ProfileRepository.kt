package com.fifty.socialnetwork.featureprofile.domain.repository

import android.net.Uri
import androidx.paging.PagingData
import com.fifty.socialnetwork.core.domain.models.Post
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.core.util.SimpleResource
import com.fifty.socialnetwork.featureprofile.domain.model.Profile
import com.fifty.socialnetwork.featureprofile.domain.model.Skill
import com.fifty.socialnetwork.featureprofile.domain.model.UpdateProfileData
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun getPostsPaged(userId: String): Flow<PagingData<Post>>

    suspend fun getProfile(userId: String): Resource<Profile>

    suspend fun updateProfile(
        updateProfileData: UpdateProfileData,
        bannerImageUri: Uri?,
        profilePictureUri: Uri?
    ): SimpleResource

    suspend fun getSkills(): Resource<List<Skill>>
}