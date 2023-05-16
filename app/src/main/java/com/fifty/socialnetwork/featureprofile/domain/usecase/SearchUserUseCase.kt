package com.fifty.socialnetwork.featureprofile.domain.usecase

import com.fifty.socialnetwork.core.domain.models.UserItem
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.featureprofile.domain.repository.ProfileRepository

class SearchUserUseCase(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(query: String): Resource<List<UserItem>> {
        if (query.isBlank()) {
            return Resource.Success(data = emptyList())
        }
        return repository.searchUser(query)
    }
}