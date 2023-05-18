package com.fifty.socialnetwork.featureprofile.domain.usecase

import android.net.Uri
import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.core.util.SimpleResource
import com.fifty.socialnetwork.core.util.UiText
import com.fifty.socialnetwork.featureprofile.domain.model.UpdateProfileData
import com.fifty.socialnetwork.core.domain.repository.ProfileRepository
import com.fifty.socialnetwork.featureprofile.domain.util.ProfileConstants

class UpdateProfileUseCase(
    private val repository: ProfileRepository
) {

    suspend operator fun invoke(
        updateProfileData: UpdateProfileData,
        profilePictureUri: Uri?,
        bannerImageUri: Uri?
    ): SimpleResource {
        if (updateProfileData.username.isBlank()) {
            return Resource.Error(
                uiText = UiText.StringResource(R.string.error_username_empty)
            )
        }
        val isValidGitHubUrl =
            ProfileConstants.GITHUB_PROFILE_REGEX.matches(updateProfileData.gitHubUrl)
        if (!isValidGitHubUrl) {
            return Resource.Error(
                uiText = UiText.StringResource(R.string.error_invalid_github_url)
            )
        }
        val isValidInstagramUrl =
            ProfileConstants.INSTAGRAM_PROFILE_REGEX.matches(updateProfileData.instagramUrl)
        if (!isValidInstagramUrl) {
            return Resource.Error(
                uiText = UiText.StringResource(R.string.error_invalid_instagram_url)
            )
        }
        val isValidLinkedInUrl =
            ProfileConstants.LINKED_IN_PROFILE_REGEX.matches(updateProfileData.linkedInUrl)
        if (!isValidLinkedInUrl) {
            return Resource.Error(
                uiText = UiText.StringResource(R.string.error_invalid_linked_in_url)
            )
        }
        return repository.updateProfile(
            updateProfileData = updateProfileData,
            profilePictureUri = profilePictureUri,
            bannerImageUri = bannerImageUri
        )
    }
}