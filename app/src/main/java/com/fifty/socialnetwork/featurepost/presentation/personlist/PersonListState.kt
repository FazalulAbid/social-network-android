package com.fifty.socialnetwork.featurepost.presentation.personlist

import com.fifty.socialnetwork.core.domain.models.UserItem

data class PersonListState(
    val users: List<UserItem> = emptyList(),
    val isLoading: Boolean = false
)
