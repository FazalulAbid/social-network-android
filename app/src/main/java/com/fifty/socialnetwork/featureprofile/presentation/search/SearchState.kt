package com.fifty.socialnetwork.featureprofile.presentation.search

import com.fifty.socialnetwork.core.domain.models.UserItem

data class SearchState(
    val userItems: List<UserItem> = emptyList(),
    val isLoading: Boolean = false
)
