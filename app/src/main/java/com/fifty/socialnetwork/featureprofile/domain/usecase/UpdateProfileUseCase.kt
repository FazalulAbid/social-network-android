package com.fifty.socialnetwork.featureprofile.domain.usecase

import android.net.Uri
import com.fifty.socialnetwork.R
import com.fifty.socialnetwork.core.util.Resource
import com.fifty.socialnetwork.core.util.SimpleResource
import com.fifty.socialnetwork.core.util.UiText
import com.fifty.socialnetwork.featureprofile.domain.model.UpdateProfileData
import com.fifty.socialnetwork.featureprofile.domain.repository.ProfileRepository

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
//        val isValidGithubUrl = updateProfileData.gitHubUrl.startsWith("https://github.com") ||
//                updateProfileData.gitHubUrl.startsWith("http://github.com") ||
//                updateProfileData.gitHubUrl.startsWith("github.com")
//        val gitHubPattern = "((http:\/\/)|(https\/\/))?".toRegex()
//        if (!isValidGithubUrl) {
//            return Resource.Error(
//                UiText.StringResource(R.string.error_invalid_github_url)
//            )
//        }
        return repository.updateProfile(
            updateProfileData = updateProfileData,
            profilePictureUri = profilePictureUri,
            bannerImageUri = bannerImageUri
        )
    }
}