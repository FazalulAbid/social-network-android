package com.fifty.socialnetwork.featureprofile.domain.repository

import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.featureprofile.domain.model.Profile

interface ProfileRepository {

    suspend fun getProfile(userId: String): Resource<Profile>
}