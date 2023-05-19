package com.fifty.socialnetwork.featureprofile.presentation.profile

import com.fifty.socialnetwork.featureprofile.domain.model.Profile


data class ProfileState(
    val profile: Profile? = null,
    val isLoading: Boolean = false,
    val isLogoutDialogVisible: Boolean = false
)
